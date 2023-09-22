package ru.goloviy.messageservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.goloviy.messageservice.event.SendMessageEvent;
import ru.goloviy.messageservice.model.Chat;
import ru.goloviy.messageservice.model.User;
import ru.goloviy.messageservice.service.EventProcessService;

import java.util.Set;

@Service
public class EventProcessServiceImpl implements EventProcessService {
    private final ApplicationEventPublisher eventPublisher;
    @Autowired
    public EventProcessServiceImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void processPrivateMessage(User userSender, User userReceiver, String text) {
        eventPublisher.publishEvent(new SendMessageEvent(this, userSender, userReceiver, text));
    }

    @Override
    public void processChatMessage(Chat chat, User sender, String text) {
        Set<User> members = chat.getMembers();
        for(User receiver: members){
            eventPublisher.publishEvent(new SendMessageEvent(this, receiver, sender, text));
        }
    }
}
