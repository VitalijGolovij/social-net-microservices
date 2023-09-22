package ru.goloviy.messageservice.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.goloviy.messageservice.dto.EmailRequest;
import ru.goloviy.messageservice.model.User;

@Getter
public class SendMessageEvent extends ApplicationEvent {
    private final EmailRequest emailRequest;
    public SendMessageEvent(Object source, User sender,User receiver, String text) {
        super(source);
        this.emailRequest = new EmailRequest(receiver.getEmail(), sender.getUsername(), text);
    }
}