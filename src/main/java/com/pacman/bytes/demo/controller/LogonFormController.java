package com.pacman.bytes.demo.controller;

import com.pacman.bytes.demo.dto.AccountDto;
import com.pacman.bytes.demo.dto.ChangePasswordDto;
import com.pacman.bytes.demo.dto.LoginRequest;
import com.pacman.bytes.demo.dto.LoginResponse;
import com.pacman.bytes.demo.service.AuthorizationService;
import com.pacman.bytes.demo.service.IAuthorizationService;
import com.pacman.bytes.demo.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LogonFormController {

    @Autowired
    IAuthorizationService authorizationService;


    @GetMapping("/beginLogon")
    public String logon(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "logon";
    }



    @PostMapping("/beginLogon")
    public ModelAndView processLogin(@ModelAttribute LoginRequest loginRequest) throws Exception{

        LoginResponse loginResponse = authorizationService.login(loginRequest);

        if (loginResponse.getIsLoginSuccessful()) {

            Optional<AccountDto> account = authorizationService.getAccount(loginRequest.getUsername());

            if (account.get().getIsPasswordTemporary()) {
                ChangePasswordDto changePasswordDto = ChangePasswordDto
                        .builder()
                        .username(account.get().getUsername())
                        .build();

                return new ModelAndView("changePassword","changePassword",changePasswordDto);
            } else {

                return new ModelAndView("update", "account", account.get());
            }
        } else {

          return new ModelAndView("logon","loginResponse",loginResponse);
        }
    }

    @PostMapping("/changePassword")
    public ModelAndView processUpdate(@ModelAttribute ChangePasswordDto changePassword) throws Exception {

        if (changePassword.getPassword().equals(changePassword.getPasswordRepeat())) {

            authorizationService.changePassword(changePassword.getUsername(),changePassword.getPassword());

            Optional<AccountDto> account = authorizationService.getAccount(changePassword.getUsername());

            return new ModelAndView("update", "account", account.get());


        } else {
            changePassword.setMessage("Passwords do not match, please try again");
            return new ModelAndView("changePassword","changePassword",changePassword);
        }

    }

        @PostMapping("/updateAccount")
    public ModelAndView processUpdate(@ModelAttribute AccountDto accountUpdate) throws Exception{

        Optional<AccountDto> accountOptional = authorizationService.getAccount(accountUpdate.getUsername());

        if (accountOptional.isPresent()) {
            AccountDto account = accountOptional.get();
            account.setSafeWord(accountUpdate.getSafeWord());
            account.setSecurityAnswer1(accountUpdate.getSecurityAnswer1());
            account.setSecurityAnswer2(accountUpdate.getSecurityAnswer2());
            account.setSecurityAnswer3(accountUpdate.getSecurityAnswer3());
            account.setTelephoneNumber(accountUpdate.getTelephoneNumber());

            authorizationService.updateAccount(account);

        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","Your account as been updated");
        modelAndView.addObject("account",accountUpdate);
        modelAndView.setViewName("update");

        return modelAndView;


    }



}
