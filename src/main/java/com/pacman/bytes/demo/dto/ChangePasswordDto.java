package com.pacman.bytes.demo.dto;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {

    private String message;

    private String username;

    private String password;

    private String passwordRepeat;

}
