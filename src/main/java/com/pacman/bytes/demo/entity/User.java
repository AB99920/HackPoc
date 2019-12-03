package com.pacman.bytes.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String username;

    private String password;

    private Boolean isLocked ;

    private Boolean isPasswordTemporary;

    private String securityAnswer1;

    private String securityAnswer2;

    private String securityAnswer3;

    private String safeWord;

    private String telephoneNumber;

    private int failedLoginCount;



}
