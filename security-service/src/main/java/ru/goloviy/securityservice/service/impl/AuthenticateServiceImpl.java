package ru.goloviy.securityservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goloviy.securityservice.dto.JwtToken;
import ru.goloviy.securityservice.dto.UserLoginCredential;
import ru.goloviy.securityservice.dto.UserRegisterCredential;
import ru.goloviy.securityservice.event.UserSaveEvent;
import ru.goloviy.securityservice.model.User;
import ru.goloviy.securityservice.repository.UserRepository;
import ru.goloviy.securityservice.service.AuthenticateService;
import ru.goloviy.securityservice.util.JwtUtil;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final ApplicationEventPublisher eventPublisher;
    @Autowired
    public AuthenticateServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.eventPublisher = eventPublisher;
    }
    @Override
    @Transactional
    public JwtToken register(UserRegisterCredential registerCredential){
        User user = modelMapper.map(registerCredential, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        eventPublisher.publishEvent(new UserSaveEvent(this, user));
        return jwtUtil.generateToken(user.getUsername());
    }
    @Override
    public JwtToken login(UserLoginCredential credential){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                credential.getUsername(),
                credential.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(token);
        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(credential.getUsername());
        }
        throw new BadCredentialsException("wrong username or password");
    }
    @Override
    public void validateToken(JwtToken token){
        jwtUtil.verifyToken(token.getToken());
    }
}
