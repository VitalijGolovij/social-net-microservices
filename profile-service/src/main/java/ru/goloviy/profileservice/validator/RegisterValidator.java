package ru.goloviy.profileservice.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.goloviy.profileservice.dto.request.UserRegister;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.repository.UserRepository;

import java.util.Objects;
import java.util.Optional;

@Component
public class RegisterValidator implements Validator {
    private final UserRepository userRepository;

    public RegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegister userRegister = (UserRegister) target;

        Optional<User> user = userRepository.getUserByUsername(userRegister.getUsername());

        if(user.isPresent()){
            errors.rejectValue("username","NotFound",
                    String.format("name '%s' is already taken", userRegister.getUsername())
            );
        }
        if (!Objects.equals(userRegister.getPassword(), userRegister.getConfirmPassword())){
            errors.rejectValue("confirmPassword","Invalid",
                    "incorrect password confirmation");
        }
    }
}
