package ru.goloviy.securityservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.goloviy.securityservice.dto.JwtToken;
import ru.goloviy.securityservice.dto.UserLoginCredential;
import ru.goloviy.securityservice.dto.UserRegisterCredential;
import ru.goloviy.securityservice.model.User;
import ru.goloviy.securityservice.repository.UserRepository;
import ru.goloviy.securityservice.util.JwtUtil;

@ExtendWith(MockitoExtension.class)
public class AuthenticateServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @InjectMocks
    private AuthenticateServiceImpl authenticateService;
    @Test
    public void successRegister(){
        JwtToken token = Mockito.mock(JwtToken.class);
        UserRegisterCredential credential = Mockito.mock(UserRegisterCredential.class);
        User user = new User();
        user.setId(1L);
        user.setPassword("pass");
        Mockito.when(modelMapper.map(credential, User.class)).thenReturn(user);
        Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPass");
        Mockito.when(jwtUtil.generateToken(user.getUsername())).thenReturn(token);

        Assertions.assertEquals(token, authenticateService.register(credential));
        Assertions.assertDoesNotThrow(() -> authenticateService.register(credential));
    }
    @Test
    public void successLogin(){
        UserLoginCredential credential = Mockito.mock(UserLoginCredential.class);
        Mockito.when(credential.getUsername()).thenReturn("username");
        Mockito.when(credential.getPassword()).thenReturn("password");
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        Mockito.when(authentication.isAuthenticated()).thenReturn(true);
        JwtToken token = Mockito.mock(JwtToken.class);
        Mockito.when(jwtUtil.generateToken(credential.getUsername())).thenReturn(token);

        Assertions.assertEquals(token, authenticateService.login(credential));
        Assertions.assertDoesNotThrow(()-> authenticateService.login(credential));
    }
    @Test
    public void badCredentialsExceptionWhenLogin(){
        UserLoginCredential credential = Mockito.mock(UserLoginCredential.class);
        Mockito.when(credential.getUsername()).thenReturn("username");
        Mockito.when(credential.getPassword()).thenReturn("password");
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        Mockito.when(authentication.isAuthenticated()).thenReturn(false);

        Assertions.assertThrows(BadCredentialsException.class, () -> authenticateService.login(credential));
    }
}
