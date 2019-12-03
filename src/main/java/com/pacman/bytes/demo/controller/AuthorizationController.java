package com.pacman.bytes.demo.controller;

import com.pacman.bytes.demo.dto.AccountDto;
import com.pacman.bytes.demo.dto.LoginRequest;
import com.pacman.bytes.demo.dto.LoginResponse;
import com.pacman.bytes.demo.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorizationController {

    @Autowired
    AuthorizationService  authorizationService;

    @PostMapping("/api/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

      return authorizationService.login(request);
    }

    @PostMapping("/api/user/${username}/unlock")
    public Boolean login(@RequestParam("username") String username)  {

         if (authorizationService.)
    }

    @GetMapping("/api/user/${username}")
    public AccountDto getAccount(@RequestParam("username") String username) {

    }



}
