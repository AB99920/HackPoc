package com.pacman.bytes.demo.controller;

import com.pacman.bytes.demo.dto.AccountDto;
import com.pacman.bytes.demo.dto.LoginRequest;
import com.pacman.bytes.demo.dto.LoginResponse;
import com.pacman.bytes.demo.exception.AccountNotFoundException;
import com.pacman.bytes.demo.service.IAuthorizationService;
import com.pacman.bytes.demo.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class AuthorizationController {

    @Autowired
    IAuthorizationService authorizationService;

    @Autowired
    PublisherService publisherService;


    @PostMapping("/api/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

      return authorizationService.login(request);
    }

    @PostMapping("/api/user/{username}/unlock")
    public void unlock(@PathVariable("username") String username)  {

        Optional<AccountDto> accountDto = authorizationService.getAccount(username);

       if (accountDto.isPresent()) {
           AccountDto updatedAccount = accountDto.get();
           updatedAccount.setIsLocked(false);
           authorizationService.updateAccount(updatedAccount);
       } else {
           throw new AccountNotFoundException(String.format("Account not found [%s]", username));
       }
    }

    @GetMapping("/api/user/{username}")
    public AccountDto getAccount(@PathVariable("username") String username) {

        Optional<AccountDto> accountDto = authorizationService.getAccount(username);

        if (accountDto.isPresent()) {
            return accountDto.get();
        } else {
            throw new AccountNotFoundException(String.format("Account not found [%s]", username));
        }
    }

    @PostMapping("/api/user/{username}/reset")
    public void reset(@PathVariable("username") String username)  {

        Optional<AccountDto> accountDto = authorizationService.getAccount(username);

        if (accountDto.isPresent()) {
            AccountDto updatedAccount = accountDto.get();
            updatedAccount.setIsLocked(false);
            updatedAccount.setIsPasswordTemporary(true);
            String pin = generatePIN();
            updatedAccount.setPassword(updatedAccount.getSafeWord()+pin);
            authorizationService.updateAccount(updatedAccount);
            try {
                publisherService.publish("Login Request Received", "");
            } catch (Exception ex ) {
                log.error("Exception thrown whilst sms'ing",ex);
            }


        } else {
            throw new AccountNotFoundException(String.format("Account not found [%s]", username));
        }
    }

    private String generatePIN()
    {

        //generate a 4 digit integer 1000 <10000
        int randomPIN = (int)(Math.random()*9000)+1000;

        return String.valueOf(randomPIN);

    }





}
