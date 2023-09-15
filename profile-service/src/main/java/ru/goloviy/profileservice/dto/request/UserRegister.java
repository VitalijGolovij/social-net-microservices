package ru.goloviy.profileservice.dto.request;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
public class UserRegister {
    @NotEmpty
    @Pattern(regexp = "^\\S+$", message = "no spaces in the username")
    private String username;
    @NotEmpty
    @Email(message = "Invalid email format")
    private String email;
    @NotEmpty
    @Min(value = 5, message = "password too short, can be greater than 5 chars")
    private String password;
    @NotEmpty(message = "password can not be empty")
    private String confirmPassword;
    @Pattern(regexp = "^\\w+$", message = "Invalid firstname format")
    private String firstname;
    @Pattern(regexp = "^\\w+$", message = "Invalid lastname format")
    private String lastname;
    @Pattern(regexp = "^\\w+$", message = "Invalid country format")
    private String country;
}
