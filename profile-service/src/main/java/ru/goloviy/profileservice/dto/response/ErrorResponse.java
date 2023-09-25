package ru.goloviy.profileservice.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message){
        this.message = message;
    }
}
