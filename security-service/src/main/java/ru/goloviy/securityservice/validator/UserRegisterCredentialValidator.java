package ru.goloviy.securityservice.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.goloviy.securityservice.dto.UserRegisterCredential;
import ru.goloviy.securityservice.model.User;
import ru.goloviy.securityservice.repository.UserRepository;

import java.util.Objects;
import java.util.Optional;

@Component
public class UserRegisterCredentialValidator implements Validator {
    private final UserRepository repository;
    @Autowired
    public UserRegisterCredentialValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterCredential credential = (UserRegisterCredential) target;
        Optional<User> usernameCheck = repository.findByUsername(credential.getUsername());
        Optional<User> emailCheck = repository.findByEmail(credential.getEmail());
        if (usernameCheck.isPresent()){
            errors.rejectValue("username","NotFound",
                    String.format("name '%s' is already taken", credential.getUsername())
            );
        }
        if (emailCheck.isPresent()){
            errors.rejectValue("email","NotFound",
                    String.format("email '%s' is already taken", credential.getEmail())
            );
        }
        if (!Objects.equals(credential.getPassword(), credential.getConfirmPassword())){
            errors.rejectValue("confirmPassword","Invalid",
                    "incorrect password confirmation");
        }
    }
}
