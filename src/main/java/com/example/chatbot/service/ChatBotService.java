package com.example.chatbot.service;

import org.springframework.stereotype.Service;

@Service
public class ChatBotService {
    public String getResponse(String userMessage) {
        if (userMessage.toLowerCase().contains("hello")) {
            return "Hi there! How can I assist you today?";
        } else if (userMessage.toLowerCase().contains("bye")) {
            return "Goodbye! Have a great day!";
        }
        return "I'm here to help! Please ask me a question.";
    }
}
