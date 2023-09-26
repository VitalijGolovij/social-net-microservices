package ru.goloviy.profileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.goloviy.profileservice.convertor.UserConvertor;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.dto.request.GetUserListRequest;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.service.PrincipalService;
import ru.goloviy.profileservice.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserConvertor userConvertor;

    @Autowired
    public UserController(UserService userService, UserConvertor userConvertor) {
        this.userService = userService;
        this.userConvertor = userConvertor;
    }

    @PostMapping("/get-user-list")
    public ResponseEntity<List<UserDto>> getUserList(@RequestBody @Valid GetUserListRequest requestBody,
                                                     BindingResult bindingResult){
        List<User> users = userService.getUserList(requestBody, bindingResult);
        List<UserDto> usersDto = userConvertor.toUserDto(users);
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        User user = userService.getUserBy(id);
        UserDto userDto = userConvertor.toUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
