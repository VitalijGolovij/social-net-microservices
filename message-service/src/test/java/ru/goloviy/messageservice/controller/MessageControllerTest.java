package ru.goloviy.messageservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import ru.goloviy.messageservice.dto.MessageRequest;
import ru.goloviy.messageservice.model.Chat;
import ru.goloviy.messageservice.model.Message;
import ru.goloviy.messageservice.model.User;
import ru.goloviy.messageservice.service.MessageService;
import ru.goloviy.messageservice.service.PrincipalService;

import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class MessageControllerTest {
    @Mock
    private MessageService messageService;
    @Mock
    private PrincipalService principalService;
    @InjectMocks
    private MessageController messageController;

    private User user;
    private Long id;
    private Message message;
    private Chat chat;
    private static HttpServletRequest request;
    @BeforeAll
    public static void initMocks(){
        request = Mockito.mock(HttpServletRequest.class);
    }
    @BeforeEach
    public void init(){
        id = 2L;
        user = new User();
        user.setId(1L);

        message = new Message();
        message.setId(1L);
        message.setOwner(user);

        chat = new Chat();
        chat.setId(1L);
        chat.setMembers(Set.of(user));

        Mockito.when(principalService.getPrincipalUser(request)).thenReturn(user);
    }
    @Test
    public void sendMessageToUserTest(){
        MessageRequest messageRequest = Mockito.mock(MessageRequest.class);
        Mockito.when(messageService.sendMessageToChat(user, id, messageRequest.getMessage())).thenReturn(message);

        ResponseEntity<Message> result = (ResponseEntity<Message>) messageController.sendMessageToChat(messageRequest, id ,request);

        Assertions.assertEquals(message, result.getBody());
    }
    @Test
    public void getChatsTest(){
        Mockito.when(messageService.getUserChats(user)).thenReturn(List.of(chat));

        Assertions.assertEquals(List.of(chat), messageController.getChats(request));
    }
    @Test
    public void getChatTest(){
        Mockito.when(messageService.getChat(user, id)).thenReturn(chat);

        ResponseEntity<Chat> result = (ResponseEntity<Chat>) messageController.getChat(request, id);
        Assertions.assertEquals(chat, result.getBody());
    }
    @Test
    public void createChatTest(){
        Mockito.when(messageService.createChat(user)).thenReturn(chat);

        ResponseEntity<Chat> result = (ResponseEntity<Chat>) messageController.createChat(request);
        Assertions.assertEquals(chat, result.getBody());
    }
    @Test
    public void addMemberToChatTest(){
        Mockito.when(messageService.addMemberToChat(user, 1L, id)).thenReturn(chat);

        ResponseEntity<Chat> result = (ResponseEntity<Chat>) messageController.addMemberToChat(request, 1L ,id);

        Assertions.assertEquals(chat, result.getBody());
    }
}
