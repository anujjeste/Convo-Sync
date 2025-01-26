package com.example.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private ChatBotService chatBotService;

    @MessageMapping("/sendMessage") // Handle messages sent to /app/sendMessage
    @SendTo("/topic/messages")     // Broadcast messages to /topic/messages
    public Message handleMessage(Message message) {
        String botResponse = chatBotService.getResponse(message.getContent());
        return new Message("Bot", botResponse); // Respond with bot's message
    }
}
