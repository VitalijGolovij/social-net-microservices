package ru.goloviy.profileservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.dto.request.GetUserListRequest;
import ru.goloviy.profileservice.exception.InvalidDataException;
import ru.goloviy.profileservice.exception.UserNotFoundException;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.repository.UserRepository;
import ru.goloviy.profileservice.service.ExampleService;
import ru.goloviy.profileservice.service.PaginationService;
import ru.goloviy.profileservice.validator.ErrorsValidationProcessor;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PaginationService paginationService;
    @Mock
    private ExampleService<User, UserDto> exampleService;
    @Mock
    private ErrorsValidationProcessor errorsValidationProcessor;
    @InjectMocks
    private UserServiceImpl userService;
    private User user1;
    @BeforeEach
    public void initUser(){
        user1 = new User();
        user1.setUsername("Bob");
    }
    @Test
    public void successGetUserByUsername(){
        String username = user1.getUsername();
        Mockito.when(userRepository.getUserByUsername(username)).thenReturn(Optional.of(user1));
        Assertions.assertEquals(user1, userService.getUserBy(username));
    }
    @Test
    public void userNotFoundByUsername(){
        String username = user1.getUsername();
        Mockito.when(userRepository.getUserByUsername(username)).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class, ()-> userService.getUserBy(username));
    }
    @Test
    public void successGetUserById(){
        Long id = user1.getId();
        Mockito.when(userRepository.getUserById(id)).thenReturn(Optional.of(user1));
        Assertions.assertEquals(user1, userService.getUserBy(id));
    }
    @Test
    public void userNotFoundById(){
        Long id = user1.getId();
        Mockito.when(userRepository.getUserById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class, ()->userService.getUserBy(id));
    }
    @Test
    public void successGetUserListFindAll(){
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user1));
        Assertions.assertEquals(List.of(user1), userService.getUserList(null, bindingResult));
    }
    @Test
    public void successGetUserListFindWithPagination(){
        Pageable pageable= Mockito.mock(Pageable.class);
        Example example = Mockito.mock(Example.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        GetUserListRequest request = Mockito.mock(GetUserListRequest.class);
        Mockito.when(paginationService.getPageable(request.getPagination())).thenReturn(pageable);
        Mockito.when(exampleService.getExample(request.getUserFilter())).thenReturn(example);
        Mockito.when(userRepository.findAll(example, pageable)).thenReturn(Page.empty());

        Assertions.assertDoesNotThrow(() -> userService.getUserList(request,bindingResult));
        Assertions.assertEquals(0, userService.getUserList(request, bindingResult).size());
    }
    @Test
    public void getUserListInvalidDataException(){
        GetUserListRequest request = Mockito.mock(GetUserListRequest.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(errorsValidationProcessor.processErrors(bindingResult)).thenReturn(Collections.emptyList());

        Assertions.assertThrows(InvalidDataException.class, ()-> userService.getUserList(request, bindingResult));
    }
}
