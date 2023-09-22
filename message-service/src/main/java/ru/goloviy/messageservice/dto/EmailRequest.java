package ru.goloviy.messageservice.dto;

import lombok.Data;

@Data
public class EmailRequest {
    private String to;
    private String subject;
    private String text;
    public EmailRequest(String to, String subject, String text){
        this.to = to;
        this.subject = subject;
        this.text = text;
    }
}
