package ru.goloviy.messageservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MessageRequest {
    @NotNull
    private Long idFrom;
    @NotNull
    private String message;
}
