package ru.goloviy.messageservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.goloviy.messageservice.exception.ChatNotFoundException;
import ru.goloviy.messageservice.exception.MemberAlreadyExistException;
import ru.goloviy.messageservice.model.Chat;
import ru.goloviy.messageservice.model.Message;
import ru.goloviy.messageservice.model.User;
import ru.goloviy.messageservice.repository.ChatRepository;
import ru.goloviy.messageservice.repository.MessageRepository;
import ru.goloviy.messageservice.service.EventProcessService;
import ru.goloviy.messageservice.service.UserService;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTest {
    @Mock
    private UserService userService;
    @Mock
    private ChatRepository chatRepository;
    @Mock
    private EventProcessService eventProcessService;
    @Mock
    private MessageRepository messageRepository;
    @InjectMocks
    private MessageServiceImpl messageService;
    private User user1;
    private User user2;
    private Chat chat;
    private Message message;
    @BeforeEach
    public void initVars(){
        user1 = new User();
        user1.setChats(new ArrayList<>());
        user1.setMessages(new ArrayList<>());
        user1.setId(1L);

        user2 = new User();
        user2.setChats(new ArrayList<>());
        user2.setMessages(new ArrayList<>());
        user2.setId(2L);

        chat = new Chat();
        chat.setId(1L);
        chat.setMessages(new ArrayList<>());

        message = new Message();
        message.setId(1L);
    }
    @Test
    public void successSendMessageToUser(){
        Long id = 2L;
        String text = "text";

        Assertions.assertDoesNotThrow(() -> messageService.sendMessageToUser(user1, id, text));
        Message result = messageService.sendMessageToUser(user1, id, text);
        Assertions.assertEquals(text, result.getMessage());
    }
    @Test
    public void successSendMessageToChat(){
        Long id = 1L;
        String text = "text";
        user1.getChats().add(chat);
        Mockito.when(chatRepository.getChatById(id)).thenReturn(Optional.of(chat));

        Assertions.assertDoesNotThrow(() -> messageService.sendMessageToChat(user1, id, text));
        Assertions.assertEquals(text, messageService.sendMessageToChat(user1, id, text).getMessage());
    }
    @Test
    public void sendMessageToChatThrowChatNotFoundException(){
        Long id = 1L;
        String text = "text";
        Mockito.when(chatRepository.getChatById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ChatNotFoundException.class, () -> messageService.sendMessageToChat(user1, id ,text));
    }
    @Test
    public void successAddMemberToChat(){
        Long chatId = 1L;
        Long userId = 2L;
        user1.addChat(chat);
        chat.addMember(user1);
        Mockito.when(chatRepository.getChatById(chatId)).thenReturn(Optional.of(chat));
        Mockito.when(userService.getUserBy(userId)).thenReturn(user2);
        Mockito.when(chatRepository.save(chat)).thenReturn(chat);

        messageService.addMemberToChat(user1, chatId, userId);

        Assertions.assertEquals(Set.of(user1, user2),chat.getMembers());
        Assertions.assertEquals(2, chat.getMembers().size());
    }
    @Test
    public void memberAlreadyExistWhenAddedMemberToChat(){
        Long chatId = 1L;
        Long userId = 2L;
        user1.addChat(chat);
        chat.addMember(user1);
        chat.addMember(user2);
        user2.addChat(chat);
        Mockito.when(chatRepository.getChatById(chatId)).thenReturn(Optional.of(chat));
        Mockito.when(userService.getUserBy(userId)).thenReturn(user2);

        Assertions.assertThrows(MemberAlreadyExistException.class, () -> messageService.addMemberToChat(user1, chatId, userId));
    }
    @Test
    public void successGetUserChats(){
        user1.addChat(chat);
        Assertions.assertEquals(List.of(chat), messageService.getUserChats(user1));
    }
    @Test
    public void successGetChat(){
        Long chatId = 1L;
        user1.addChat(chat);
        Mockito.when(chatRepository.getChatById(chatId)).thenReturn(Optional.of(chat));
        Assertions.assertEquals(chat, messageService.getChat(user1, chatId));
    }
    @Test
    public void ChatNotFoundWhenGetChat(){
        Long chatId = 1L;
        Mockito.when(chatRepository.getChatById(chatId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ChatNotFoundException.class, () -> messageService.getChat(user1, chatId));
    }
}
