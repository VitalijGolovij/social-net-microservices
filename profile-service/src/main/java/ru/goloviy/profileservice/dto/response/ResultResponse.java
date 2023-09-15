package ru.goloviy.profileservice.dto.response;

import lombok.Getter;

@Getter
public abstract class ResultResponse {
    private final String code;
    public ResultResponse(String code){
        this.code = code;
    }
}
