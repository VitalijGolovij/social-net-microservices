package ru.goloviy.emailsenderservice.service;

import ru.goloviy.emailsenderservice.dto.EmailRequest;

public interface EmailService {
    void saveMail(EmailRequest request);
}
