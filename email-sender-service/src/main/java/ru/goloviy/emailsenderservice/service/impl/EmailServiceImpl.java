package ru.goloviy.emailsenderservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.goloviy.emailsenderservice.convertor.EmailConvertor;
import ru.goloviy.emailsenderservice.dto.EmailRequest;
import ru.goloviy.emailsenderservice.model.Email;
import ru.goloviy.emailsenderservice.repository.EmailRepository;
import ru.goloviy.emailsenderservice.service.EmailService;
@Service
public class EmailServiceImpl implements EmailService {
    private final EmailConvertor emailConvertor;
    private final EmailRepository emailRepository;
    @Autowired
    public EmailServiceImpl(EmailConvertor emailConvertor, EmailRepository emailRepository) {
        this.emailConvertor = emailConvertor;
        this.emailRepository = emailRepository;
    }

    @Override
    public void saveMail(EmailRequest request) {
        Email email = emailConvertor.toMailMessage(request);
        emailRepository.save(email);
    }
}
