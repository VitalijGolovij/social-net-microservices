package ru.goloviy.profileservice.dto.request;

import lombok.Getter;

@Getter
public class UserLogin {
    private String username;
    private String password;
}
