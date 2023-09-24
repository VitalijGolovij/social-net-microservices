package ru.goloviy.securityservice.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.goloviy.securityservice.dto.UserRegisterCredential;
import ru.goloviy.securityservice.model.UserCredential;
import ru.goloviy.securityservice.repository.UserCredentialRepository;

import java.util.Objects;
import java.util.Optional;

@Component
public class UserRegisterCredentialValidator implements Validator {
    private final UserCredentialRepository repository;
    @Autowired
    public UserRegisterCredentialValidator(UserCredentialRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterCredential credential = (UserRegisterCredential) target;
        Optional<UserCredential> userCredentialOptional = repository.findByUsername(credential.getUsername());
        if (userCredentialOptional.isPresent()){
            errors.rejectValue("username","NotFound",
                    String.format("name '%s' is already taken", credential.getUsername())
            );
        }
        if (!Objects.equals(credential.getPassword(), credential.getConfirmPassword())){
            errors.rejectValue("confirmPassword","Invalid",
                    "incorrect password confirmation");
        }
    }
}
