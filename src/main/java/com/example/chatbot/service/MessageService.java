package com.example.chatbot.service;

import com.example.chatbot.entity.Message;
import com.example.chatbot.repository.MessageRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Save a message
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    // Retrieve messages for a specific recipient
    public List<Message> getMessagesForRecipient(String recipient) {
        return messageRepository.findByRecipient(recipient);
    }
}

