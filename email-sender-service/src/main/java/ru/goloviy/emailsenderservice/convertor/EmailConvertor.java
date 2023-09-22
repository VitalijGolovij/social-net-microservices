package ru.goloviy.emailsenderservice.convertor;

import ru.goloviy.emailsenderservice.dto.EmailRequest;
import ru.goloviy.emailsenderservice.model.Email;

public interface EmailConvertor {
    Email toMailMessage(EmailRequest request);
}
