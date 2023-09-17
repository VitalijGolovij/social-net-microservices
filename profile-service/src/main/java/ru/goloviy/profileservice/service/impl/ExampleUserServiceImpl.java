package ru.goloviy.profileservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;
import ru.goloviy.profileservice.convertor.UserConvertor;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.service.ExampleService;

@Component
public class ExampleUserServiceImpl implements ExampleService<User, UserDto> {
    private final UserConvertor userConvertor;

    @Autowired
    public ExampleUserServiceImpl(UserConvertor userConvertor) {
        this.userConvertor = userConvertor;
    }

    @Override
    public Example<User> getExample(UserDto filter) {
        if (filter == null){
            return Example.of(new User(), ExampleMatcher.matchingAll());
        }
        User user = userConvertor.toUser(filter);
        return Example.of(user, getMatcher());
    }

    private ExampleMatcher getMatcher(){
        return ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnorePaths("id");
    }
}
