package com.pacman.bytes.demo.dto;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto  {

    private String username;

    private String password;

    private Boolean isLocked ;

    private Boolean isPasswordTemporary;

    private String securityAnswer1;

    private String securityAnswer2;

    private String securityAnswer3;

    private String safeWord;

    private String telephoneNumber;

}
