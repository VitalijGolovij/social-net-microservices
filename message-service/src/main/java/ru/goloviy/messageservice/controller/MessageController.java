package ru.goloviy.messageservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.goloviy.messageservice.dto.MessageRequest;
import ru.goloviy.messageservice.model.Chat;
import ru.goloviy.messageservice.model.Message;
import ru.goloviy.messageservice.model.User;
import ru.goloviy.messageservice.service.MessageService;
import ru.goloviy.messageservice.service.PrincipalService;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class MessageController {
    private final MessageService messageService;
    private final PrincipalService principalService;

    @Autowired
    public MessageController(MessageService messageService, PrincipalService principalService) {
        this.messageService = messageService;
        this.principalService = principalService;
    }

    @PostMapping("/send-to-user/{userId}")
    @Operation(summary = "send a message to a user by id")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<Message> sendMessageToUser(@RequestBody MessageRequest requestMessage,
                                                     @PathVariable Long userId,
                                                     HttpServletRequest request){
        User principalUser = principalService.getPrincipalUser(request);
        messageService.sendMessageToUser(principalUser, userId, requestMessage.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/send-to-chat/{chatId}")
    @Operation(summary = "send a message to the chat room by chat id")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<?> sendMessageToChat(@RequestBody MessageRequest requestMessage,
                                               @PathVariable Long chatId,
                                               HttpServletRequest request){
        User principalUser = principalService.getPrincipalUser(request);
        messageService.sendMessageToChat(principalUser, chatId, requestMessage.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "get all chats available to the user")
    @SecurityRequirement(name = "JWT")
    public List<Chat> getChats(HttpServletRequest request){
        User principalUser = principalService.getPrincipalUser(request);
        return messageService.getUserChats(principalUser);
    }
    @GetMapping("/{chatId}")
    @Operation(summary = "get the chat available to the user by id")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<?> getChat(HttpServletRequest request,
                                     @PathVariable Long chatId){
        User principalUser = principalService.getPrincipalUser(request);
        Chat chat = messageService.getChat(principalUser, chatId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
}
