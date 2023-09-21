package ru.goloviy.messageservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
