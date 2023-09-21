package ru.goloviy.messageservice.service;

import ru.goloviy.messageservice.model.Chat;
import ru.goloviy.messageservice.model.Message;

import java.util.List;

public interface MessageService {
    void sendMessage(Long fromUserId, Long toUserId, String text);
    void sendMessage(Long chatId, String text, Long senderUserId);

    List<Chat> getUserChats(Long userId);

    Chat getChat(Long userId, Long chatId);
}
