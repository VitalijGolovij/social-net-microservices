package ru.goloviy.profileservice.convertor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.goloviy.profileservice.convertor.UserConvertor;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.dto.request.UserRegister;
import ru.goloviy.profileservice.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConvertorImpl implements UserConvertor {
    private final ModelMapper modelMapper;

    @Autowired
    public UserConvertorImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User toUser(UserRegister userRegister) {
        User user = new User();
        modelMapper.map(userRegister, user);
        return user;
    }

    @Override
    public User toUser(UserDto userDto) {
        User user = new User();
        modelMapper.map(userDto, user);
        return user;
    }

    @Override
    public List<UserDto> toUserDto(List<User> users){
        return users.stream().map(this::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        modelMapper.map(user, userDto);
        return userDto;
    }
}
