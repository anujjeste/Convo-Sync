package com.example.chatbot.repository;

import com.example.chatbot.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByRecipient(String recipient);
}
