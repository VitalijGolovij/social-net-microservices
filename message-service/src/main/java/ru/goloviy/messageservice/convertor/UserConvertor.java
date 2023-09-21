package ru.goloviy.messageservice.convertor;

import ru.goloviy.messageservice.dto.UserDto;
import ru.goloviy.messageservice.model.User;

public interface UserConvertor {
    public UserDto toUserDto(User user);
}
