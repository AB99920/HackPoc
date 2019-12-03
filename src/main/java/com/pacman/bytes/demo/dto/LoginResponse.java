package com.pacman.bytes.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponse {

    private Boolean isLoginSuccessful;

    private Boolean isAccountLocked;

    private Boolean isPasswordTemporary;

    private Boolean isResetQuestionsAvailable;

}
