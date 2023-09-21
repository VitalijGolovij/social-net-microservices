package ru.goloviy.messageservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goloviy.messageservice.dto.MessageRequest;
import ru.goloviy.messageservice.model.Chat;
import ru.goloviy.messageservice.model.Message;
import ru.goloviy.messageservice.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send-to-user/{receiverId}")
    public ResponseEntity<Message> sendMessageToUser(@RequestBody MessageRequest request,
                                                     @PathVariable Long receiverId){
       messageService.sendMessage(request.getIdFrom(), receiverId, request.getMessage());
       return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/send-to-chat/{chatId}")
    public ResponseEntity<?> sendMessageToChat(@RequestBody MessageRequest request,
                                               @PathVariable Long chatId){
        messageService.sendMessage(chatId, request.getMessage(), request.getIdFrom());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public List<Chat> getChats(@PathVariable Long userId){
        List<Chat> chats = messageService.getUserChats(userId);
        return chats;
    }
    @GetMapping("user/{userId}/get/{chatId}")
    public ResponseEntity<?> getChat(@PathVariable Long userId,
                                     @PathVariable Long chatId){
        Chat chat = messageService.getChat(userId, chatId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
}
