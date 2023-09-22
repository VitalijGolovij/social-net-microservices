package ru.goloviy.messageservice.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.goloviy.messageservice.dto.EmailRequest;

@Service
public class EmailKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailKafkaProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(EmailRequest data){
        LOGGER.info(String.format("Message send: %s", data.toString()));
        try {
            Message<EmailRequest> message = MessageBuilder
                    .withPayload(data)
                    .setHeader(KafkaHeaders.TOPIC, "Email")
                    .build();
            kafkaTemplate.send(message);
        } catch (Exception e){
            LOGGER.info(String.format("cannot send message: %s", data.toString()));
        }
    }
}
