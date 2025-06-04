package com.example.chatbot.controller;

import com.example.chatbot.entity.Message;
import com.example.chatbot.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final MessageService messageService;

    @Value("${websocket.broker.prefix}")
    private String topicPrefix;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat")
    @SendTo("${websocket.broker.prefix}/messages")
    public Message handleMessage(Message message) {
        System.out.println("Received message: " + message.getContent());
        return messageService.saveMessage(message);
    }
}
