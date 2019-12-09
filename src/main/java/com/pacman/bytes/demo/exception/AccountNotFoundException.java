package com.pacman.bytes.demo.exception;

public class AccountNotFoundException extends RuntimeException {


    public AccountNotFoundException(String message) {
        super(message);
    }
}
