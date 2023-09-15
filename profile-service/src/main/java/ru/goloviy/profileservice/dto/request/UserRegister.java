package ru.goloviy.profileservice.dto.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class UserRegister {
    @NotEmpty
    private String username;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Min(5)
    private String password;
    @NotEmpty
    private String confirmPassword;
}
