package ru.goloviy.messageservice.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.goloviy.messageservice.dto.EmailRequest;
import ru.goloviy.messageservice.event.SendMessageEvent;
import ru.goloviy.messageservice.kafka.producer.EmailKafkaProducer;

@Component
public class SendMessageEventListener {
    private final EmailKafkaProducer kafkaProducer;
    @Autowired
    public SendMessageEventListener(EmailKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    @EventListener
    public void handleSendMessageEvent(SendMessageEvent e){
        EmailRequest request = e.getEmailRequest();
        kafkaProducer.sendMessage(request);
    }
}