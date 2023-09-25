package ru.goloviy.apigateway.dto;

import lombok.Data;

@Data
public class UnAuthorizeResponse {
    private String message;
    public UnAuthorizeResponse(String message){
        this.message = message;
    }
}
