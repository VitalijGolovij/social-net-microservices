package ru.goloviy.profileservice.convertor;

import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.dto.request.UserRegister;
import ru.goloviy.profileservice.model.User;

public interface UserConvertor {
    User toUser(UserRegister userRegister);
    UserDto toUserDto(User user);
}
