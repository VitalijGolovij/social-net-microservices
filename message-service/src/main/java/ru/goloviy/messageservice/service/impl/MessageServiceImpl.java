package ru.goloviy.messageservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goloviy.messageservice.exception.ChatNotFoundException;
import ru.goloviy.messageservice.model.Chat;
import ru.goloviy.messageservice.model.Message;
import ru.goloviy.messageservice.model.User;
import ru.goloviy.messageservice.repository.ChatRepository;
import ru.goloviy.messageservice.service.MessageService;
import ru.goloviy.messageservice.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {
    private final UserService userService;
    private final ChatRepository chatRepository;

    @Autowired
    public MessageServiceImpl(UserService userService, ChatRepository chatRepository) {
        this.userService = userService;
        this.chatRepository = chatRepository;
    }
    @Override
    @Transactional
    public void sendMessage(Long fromUserId, Long toUserId, String text) {
        User userSender = userService.getUserBy(fromUserId);
        User userReceiver = userService.getUserBy(toUserId);
        Message message = new Message(userSender, text);
        Set<User> chatMembers = new HashSet<>(){{add(userReceiver); add(userSender);}};

        Chat chat = getPrivateChat(userSender, userReceiver);
        if (chat == null){
            chat = new Chat(chatMembers);
        }
        chat.addMessage(message);
        message.setChat(chat);

        chatRepository.save(chat);
    }

    @Override
    @Transactional
    public void sendMessage(Long chatId, String text, Long senderUserId) {
        Chat chat = getChatBy(chatId);
        User user = userService.getUserBy(senderUserId);
        Message message = new Message(user, text);
        chat.addMessage(message);
        message.setChat(chat);
        chatRepository.save(chat);
    }
    @Override
    public List<Chat> getUserChats(Long userId){
        User user = userService.getUserBy(userId);
        return user.getChats();
    }
    @Override
    public Chat getChat(Long userId, Long chatId){
        User user = userService.getUserBy(userId);
        Chat chat = getChatBy(chatId);
        if (user.getChats().contains(chat))
            return chat;
        throw new ChatNotFoundException(chatId);
    }
    private Chat getChatBy(Long id){
        Optional<Chat> chat = chatRepository.getChatById(id);
        if (chat.isEmpty())
            throw new ChatNotFoundException(id);
        return chat.get();
    }
    private Chat getPrivateChat(User user1, User user2){
        if (user1.getChats() == null)
            return null;

        List<Chat> chats = user1.getChats().stream().filter(
                c -> c.getMembers().size() == 2 && c.getMembers().contains(user2)
        ).toList();
        return chats.isEmpty() ? null : chats.get(0);
    }
    //TODO доделать методы, синхронизация бд по кафке, отправка email по кафке, DTO для User
}
