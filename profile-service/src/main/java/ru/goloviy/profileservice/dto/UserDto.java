package ru.goloviy.profileservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String country;
    private String firstname;
    private String lastname;
}
