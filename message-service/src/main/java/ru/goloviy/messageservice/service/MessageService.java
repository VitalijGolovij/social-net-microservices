package ru.goloviy.messageservice.service;

import ru.goloviy.messageservice.model.Chat;
import ru.goloviy.messageservice.model.Message;
import ru.goloviy.messageservice.model.User;

import java.util.List;

public interface MessageService {
    Message sendMessageToUser(User userSender, Long toUserId, String text);
    Message sendMessageToChat(User userSender, Long chatId, String text);
    Chat createChat(User principalUser);
    Chat addMemberToChat(User principalUser, Long chatId, Long userId);
    List<Chat> getUserChats(User user);
    Chat getChat(User principalUser, Long chatId);
}
