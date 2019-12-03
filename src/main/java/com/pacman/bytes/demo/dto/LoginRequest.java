package com.pacman.bytes.demo.dto;


import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String username;

    private String password;


}
