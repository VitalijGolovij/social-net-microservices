package ru.goloviy.profileservice.dto.response;

import lombok.Getter;
import ru.goloviy.profileservice.dto.UserDto;

import java.util.List;

@Getter
public class UserListResponse {
    private List<UserDto> users;
    public UserListResponse(List<UserDto> users){
        this.users = users;
    }
}
