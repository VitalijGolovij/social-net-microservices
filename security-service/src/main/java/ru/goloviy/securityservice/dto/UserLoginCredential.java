package ru.goloviy.securityservice.dto;

import lombok.Data;

@Data
public class UserLoginCredential {
    private String username;
    private String password;
}
