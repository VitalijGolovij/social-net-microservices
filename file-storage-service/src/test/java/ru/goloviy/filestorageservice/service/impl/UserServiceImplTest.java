package ru.goloviy.filestorageservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.goloviy.filestorageservice.exception.UserNotFoundException;
import ru.goloviy.filestorageservice.model.User;
import ru.goloviy.filestorageservice.repository.UserRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    @BeforeEach
    public void initUser(){
        user = new User();
        user.setId(1L);
    }
    @Test
    public void successGetUserById(){
        Long id = 1L;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Assertions.assertEquals(user, userService.getUserBy(id));
    }
    @Test
    public void userNotFoundWhenGetUserById(){
        Long id = 1L;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, ()-> userService.getUserBy(id));
    }
    @Test
    public void successGetUserByUsername(){
        String username = "username";
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Assertions.assertEquals(user, userService.getUserBy(username));
    }
    @Test
    public void userNotFoundWhenGetUserByUsername(){
        String username = "username";
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, ()-> userService.getUserBy(username));
    }
}
