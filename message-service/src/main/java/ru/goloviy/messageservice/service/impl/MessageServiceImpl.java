package ru.goloviy.messageservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goloviy.messageservice.exception.ChatNotFoundException;
import ru.goloviy.messageservice.exception.MemberAlreadyExistException;
import ru.goloviy.messageservice.model.Chat;
import ru.goloviy.messageservice.model.Message;
import ru.goloviy.messageservice.model.User;
import ru.goloviy.messageservice.repository.ChatRepository;
import ru.goloviy.messageservice.repository.MessageRepository;
import ru.goloviy.messageservice.service.EventProcessService;
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
    private final EventProcessService eventProcessService;
    private final MessageRepository messageRepository;


    @Autowired
    public MessageServiceImpl(UserService userService, ChatRepository chatRepository, EventProcessService eventProcessService, MessageRepository messageRepository) {
        this.userService = userService;
        this.chatRepository = chatRepository;
        this.eventProcessService = eventProcessService;
        this.messageRepository = messageRepository;
    }
    @Override
    @Transactional
    public Message sendMessageToUser(User userSender, Long toUserId, String text) {
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
        messageRepository.save(message);
        eventProcessService.processPrivateMessage(userSender, userReceiver, text);
        return message;
    }

    @Override
    @Transactional
    public Message sendMessageToChat(User userSender, Long chatId, String text) {
        Chat chat = getChat(userSender, chatId);
        Message message = new Message(userSender, text);
        chat.addMessage(message);
        message.setChat(chat);
        chatRepository.save(chat);
        messageRepository.save(message);
        eventProcessService.processChatMessage(chat, userSender, text);
        return message;
    }

    @Override
    @Transactional
    public Chat createChat(User principalUser) {
        Chat newChat = new Chat();
        newChat.addMember(principalUser);
        principalUser.addChat(newChat);
        return chatRepository.save(newChat);
    }

    @Override
    @Transactional
    public Chat addMemberToChat(User principalUser, Long chatId, Long userId) {
        Chat chat = getChat(principalUser, chatId);
        User newMember = userService.getUserBy(userId);
        if (chat.getMembers().contains(newMember))
            throw new MemberAlreadyExistException(chatId, userId);
        chat.addMember(newMember);
        newMember.addChat(chat);
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> getUserChats(User user){
        return user.getChats();
    }
    @Override
    public Chat getChat(User user, Long chatId){
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
}
