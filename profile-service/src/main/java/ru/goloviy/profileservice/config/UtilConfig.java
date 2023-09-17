package ru.goloviy.profileservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.dto.response.UserListResponse;

import java.util.List;

@Configuration
public class UtilConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    @Scope(scopeName = "prototype")
    public UserListResponse userListResponse(List<UserDto> userDtoList){
        return new UserListResponse(userDtoList);
    }
}
