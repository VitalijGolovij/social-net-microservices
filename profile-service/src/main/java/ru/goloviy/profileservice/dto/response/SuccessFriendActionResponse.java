package ru.goloviy.profileservice.dto.response;

import lombok.Data;
import ru.goloviy.profileservice.dto.UserDto;

import java.time.LocalDateTime;

@Data
public class SuccessFriendActionResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private final String message = "success";
    private UserDto userInfo;
    public SuccessFriendActionResponse(UserDto userInfo){
        this.userInfo = userInfo;
    }
}
