package ru.goloviy.emailsenderservice.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.goloviy.emailsenderservice.dto.EmailRequest;
import ru.goloviy.emailsenderservice.service.EmailSenderService;
import ru.goloviy.emailsenderservice.service.EmailService;

@Service
public class EmailKafkaConsumer {
    private final EmailSenderService emailSenderService;
    private final EmailService emailService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailKafkaConsumer.class);
    @Autowired
    public EmailKafkaConsumer(EmailSenderService emailSenderService, EmailService emailService) {
        this.emailSenderService = emailSenderService;
        this.emailService = emailService;
    }
    @KafkaListener(topics = "Email", groupId = "group_id")
    public void sendEmail(EmailRequest message) {
        try {
            emailSenderService.sendEmail(message);
            emailService.saveMail(message);
        } catch (Exception e){
            LOGGER.info("cannot send email message: " + e.getMessage());
        }
    }
}
