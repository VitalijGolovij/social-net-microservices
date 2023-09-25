package ru.goloviy.securityservice.dto.response;

import lombok.Getter;
import ru.goloviy.securityservice.dto.response.ExceptionResponse;

import java.util.List;
import java.util.Map;

@Getter
public class ValidateExceptionResponse extends ExceptionResponse {
    List<Map<String, String>> errorFields;
    public ValidateExceptionResponse(String code, String message, List<Map<String, String>> errorFields) {
        super(code, message);
        this.errorFields = errorFields;
    }
}