package ru.goloviy.profileservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import ru.goloviy.profileservice.convertor.UserConvertor;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.model.User;

@ExtendWith(MockitoExtension.class)
public class ExampleUserServiceImplTest {
    @Mock
    private UserConvertor userConvertor;
    @InjectMocks
    private ExampleUserServiceImpl exampleUserService;
    @Test
    public void getExampleNotNullCaseTest(){
        UserDto userDto = new UserDto();
        userDto.setId(1l);
        User user = new User();
        user.setId(1l);
        ExampleMatcher matcher = ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                        .withIgnorePaths("id");
        Example<User> expect = Example.of(user, matcher);

        Mockito.when(userConvertor.toUser(userDto)).thenReturn(user);

        Assertions.assertEquals(exampleUserService.getExample(userDto), expect);
    }

    @Test
    public void getExampleNullCaseTest(){
        UserDto userDto = null;
        Example<User> expect = Example.of(new User(), ExampleMatcher.matchingAll());
        Assertions.assertEquals(exampleUserService.getExample(userDto), expect);
    }
}

