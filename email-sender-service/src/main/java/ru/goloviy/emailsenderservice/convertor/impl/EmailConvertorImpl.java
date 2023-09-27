package ru.goloviy.emailsenderservice.convertor.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.goloviy.emailsenderservice.convertor.EmailConvertor;
import ru.goloviy.emailsenderservice.dto.EmailRequest;
import ru.goloviy.emailsenderservice.model.Email;

@Component
public class EmailConvertorImpl implements EmailConvertor {
    @Value("${spring.mail.from}")
    private String from;
    @Override
    public Email toMailMessage(EmailRequest request) {
        Email email = new Email();

        email.setFrom(from);
        email.setTo(request.getTo());
        email.setText(request.getText());

        return email;
    }
}
