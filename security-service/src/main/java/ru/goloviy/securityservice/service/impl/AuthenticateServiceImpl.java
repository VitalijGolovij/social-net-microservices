package ru.goloviy.securityservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import ru.goloviy.securityservice.model.UserCredential;
import ru.goloviy.securityservice.repository.UserCredentialRepository;
import ru.goloviy.securityservice.service.AuthenticateService;
import ru.goloviy.securityservice.util.JwtUtil;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    @Autowired
    public AuthenticateServiceImpl(UserCredentialRepository userCredentialRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }
    @Override
    @Transactional
    public JwtToken register(UserRegisterCredential registerCredential){
        UserCredential userCredential = modelMapper.map(registerCredential, UserCredential.class);
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        userCredentialRepository.save(userCredential);
        return jwtUtil.generateToken(userCredential.getUsername());
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
