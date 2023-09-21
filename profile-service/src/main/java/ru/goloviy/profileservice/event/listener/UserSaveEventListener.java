package ru.goloviy.profileservice.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.goloviy.profileservice.event.UserSaveEvent;
import ru.goloviy.profileservice.kafka.JsonKafkaProducer;
import ru.goloviy.profileservice.model.User;

@Component
public class UserSaveEventListener {
    private final JsonKafkaProducer kafkaProducer;
    @Autowired
    public UserSaveEventListener(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    @EventListener
    public void handleUserSaveEvent(UserSaveEvent e){
        User user = e.getUser();
        kafkaProducer.sendMessage(user);
    }
}
