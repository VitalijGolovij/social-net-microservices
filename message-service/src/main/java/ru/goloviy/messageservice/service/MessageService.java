package ru.goloviy.messageservice.service;

import ru.goloviy.messageservice.model.Chat;
import ru.goloviy.messageservice.model.User;

import java.util.List;

public interface MessageService {
    void sendMessageToUser(User userSender, Long toUserId, String text);
    void sendMessageToChat(User userSender, Long chatId, String text);
    List<Chat> getUserChats(User user);
    Chat getChat(User principalUser, Long chatId);
}
