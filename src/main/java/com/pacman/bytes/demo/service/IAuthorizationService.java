package com.pacman.bytes.demo.service;

import com.pacman.bytes.demo.dto.AccountDto;
import com.pacman.bytes.demo.dto.LoginRequest;
import com.pacman.bytes.demo.dto.LoginResponse;

import java.util.Optional;

public interface IAuthorizationService {
    LoginResponse login(LoginRequest request);

    void updateAccount(AccountDto accountDto);

    boolean changePassword(String username, String password);

    Optional<AccountDto> getAccount(String username);
}
