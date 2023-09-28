package ru.goloviy.messageservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.goloviy.messageservice.exception.UserNotFoundException;
import ru.goloviy.messageservice.model.User;
import ru.goloviy.messageservice.repository.UserRepository;

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
        Mockito.when(userRepository.getUserById(id)).thenReturn(user);
        Assertions.assertEquals(user, userService.getUserBy(id));
    }
    @Test
    public void userNotFindWhenGetById(){
        Long id = 1L;
        Mockito.when(userRepository.getUserById(id)).thenReturn(null);
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserBy(id));
    }
    @Test
    public void successGetUserByUsername(){
        String username = "Bob";
        user.setUsername(username);
        Mockito.when(userRepository.getUserByUsername(username)).thenReturn(user);
        Assertions.assertEquals(user, userService.getUserBy(username));
    }
    @Test
    public void userNotFindWhenGetUserByUsername(){
        String username = "Bob";
        Mockito.when(userRepository.getUserByUsername(username)).thenReturn(null);
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserBy(username));
    }
}
