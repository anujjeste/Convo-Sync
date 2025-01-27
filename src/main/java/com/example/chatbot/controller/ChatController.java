package com.example.chatbot.controller;

import com.example.chatbot.entity.Message;
import com.example.chatbot.service.ChatBotService;
import com.example.chatbot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private ChatBotService chatBotService;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message handleMessage(Message message) {
        // Save user message to the database
        messageService.saveMessage(message);

        // Get chatbot response if applicable
        String botResponse = chatBotService.getResponse(message.getContent());
        Message botMessage = new Message("Bot", botResponse, message.getSender(), System.currentTimeMillis());

        // Save bot response to the database
        messageService.saveMessage(botMessage);

        // Return the bot's response
        return botMessage;
    }
}
