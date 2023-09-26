package ru.goloviy.securityservice.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.goloviy.securityservice.event.UserSaveEvent;
import ru.goloviy.securityservice.kafka.producer.UserKafkaProducer;
import ru.goloviy.securityservice.model.User;

@Component
public class UserSaveEventListener {
    private final UserKafkaProducer kafkaProducer;
    @Autowired
    public UserSaveEventListener(UserKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    @EventListener
    public void handleUserSaveEvent(UserSaveEvent e){
        User user = e.getUser();
        kafkaProducer.sendMessage(user);
    }
}
