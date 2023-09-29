package ru.goloviy.profileservice.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ru.goloviy.profileservice.convertor.UserConvertor;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.dto.request.GetUserListRequest;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.service.UserService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private UserConvertor userConvertor;
    @InjectMocks
    private UserController userController;
    private User user;
    private UserDto userDto;
    @BeforeEach
    public void init(){
        user = new User();
        user.setId(1L);
        user.setUsername("user");

        userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
    }
    @Test
    public void getUserListTest(){
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        GetUserListRequest userListRequest = Mockito.mock(GetUserListRequest.class);
        List<User> users = List.of(user);
        List<UserDto> usersDto = List.of(userDto);
        Mockito.when(userService.getUserList(userListRequest,bindingResult)).thenReturn(users);
        Mockito.when(userConvertor.toUserDto(users)).thenReturn(usersDto);

        ResponseEntity<List<UserDto>> expect = new ResponseEntity<>(usersDto, HttpStatus.OK);

        Assertions.assertEquals(expect, userController.getUserList(userListRequest, bindingResult));
    }
    @Test
    public void getUserTest(){
        Long id = 1L;
        Mockito.when(userService.getUserBy(id)).thenReturn(user);
        Mockito.when(userConvertor.toUserDto(user)).thenReturn(userDto);

        ResponseEntity<UserDto> expect = new ResponseEntity<>(userDto, HttpStatus.OK);

        Assertions.assertEquals(expect, userController.getUser(id));
    }
}
