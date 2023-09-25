package ru.goloviy.profileservice.convertor;

import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.dto.request.UserRegister;
import ru.goloviy.profileservice.model.User;

import java.util.List;
import java.util.Set;

public interface UserConvertor {
    User toUser(UserRegister userRegister);
    User toUser(UserDto userDto);
    List<UserDto> toUserDto(List<User> users);
    List<UserDto> toUserDto(Set<User> users);
    UserDto toUserDto(User user);
}
