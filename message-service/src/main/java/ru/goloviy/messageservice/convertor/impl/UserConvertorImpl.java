package ru.goloviy.messageservice.convertor.impl;

import com.netflix.discovery.converters.Auto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.goloviy.messageservice.convertor.UserConvertor;
import ru.goloviy.messageservice.dto.UserDto;
import ru.goloviy.messageservice.model.User;

@Component
public class UserConvertorImpl implements UserConvertor {
    private final ModelMapper modelMapper;
    @Autowired
    public UserConvertorImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto toUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
