package ru.goloviy.securityservice.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.goloviy.securityservice.model.User;

@Service
public class UserKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserKafkaProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User data){
        LOGGER.info(String.format("Message send: %s", data.toString()));
        try {
            Message<User> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "TestTopic")
                .build();
            kafkaTemplate.send(message);
        } catch (Exception e){
            LOGGER.info(String.format("cannot send message: %s", data.toString()));
        }
    }
}
