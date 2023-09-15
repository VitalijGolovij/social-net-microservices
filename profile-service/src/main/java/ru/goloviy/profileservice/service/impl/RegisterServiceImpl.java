package ru.goloviy.profileservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.goloviy.profileservice.convertor.UserConvertor;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.dto.request.UserRegister;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.repository.UserRepository;
import ru.goloviy.profileservice.service.RegisterService;
import ru.goloviy.profileservice.util.JwtTokenResponse;
import ru.goloviy.profileservice.util.JwtUtil;
import ru.goloviy.profileservice.validator.RegisterValidator;

import java.util.List;

@Component
public class RegisterServiceImpl implements RegisterService {
    private final RegisterValidator registerValidator;
    private final UserConvertor userConvertor;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public RegisterServiceImpl(RegisterValidator registerValidator, UserConvertor userConvertor, PasswordEncoder passwordEncoder, UserRepository userRepository, JwtUtil jwtUtil) {
        this.registerValidator = registerValidator;
        this.userConvertor = userConvertor;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public JwtTokenResponse register(UserRegister userRegister, BindingResult bindingResult) {
        registerValidator.validate(userRegister, bindingResult);

        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            //TODO
            throw new RuntimeException();
        }
        User user = userConvertor.toUser(userRegister);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return jwtUtil.generateToken(user.getUsername());
    }
}
