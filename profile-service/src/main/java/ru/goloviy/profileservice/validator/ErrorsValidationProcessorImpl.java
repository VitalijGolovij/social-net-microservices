package ru.goloviy.profileservice.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ErrorsValidationProcessorImpl implements ErrorsValidationProcessor{
    @Override
    public List<Map<String, String>>processErrors(BindingResult bindingResult){
        List<FieldError> errors = bindingResult.getFieldErrors();
        List<Map<String, String>> errorList = new ArrayList<>();

        for (FieldError error: errors){
            errorList.add(Map.of("field",error.getField(),
                    "message", Objects.requireNonNull(error.getDefaultMessage())));
        }
        return  errorList;
    }
}
