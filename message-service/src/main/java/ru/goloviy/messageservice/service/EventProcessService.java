package ru.goloviy.messageservice.service;

import ru.goloviy.messageservice.model.Chat;
import ru.goloviy.messageservice.model.User;

public interface EventProcessService {
    void processPrivateMessage(User userSender, User userReceiver, String text);
    void processChatMessage(Chat chat, User sender, String text);
}