package ru.goloviy.securityservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegisterCredential {
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

    private String firstname;
    private String lastname;
    private String country;
}
