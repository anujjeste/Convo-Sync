package com.example.chatbot.service;

import com.example.chatbot.entity.Message;
import com.example.chatbot.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    // Save a message
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    // Retrieve messages for a specific recipient
    public List<Message> getMessagesForRecipient(String recipient) {
        return messageRepository.findByRecipient(recipient);
    }
}
