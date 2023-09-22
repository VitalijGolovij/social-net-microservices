package ru.goloviy.emailsenderservice.service;

import ru.goloviy.emailsenderservice.dto.EmailRequest;

public interface EmailSenderService {
    void sendEmail(EmailRequest emailRequest);
}
