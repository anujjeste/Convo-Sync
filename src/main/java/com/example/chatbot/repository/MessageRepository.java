package com.example.chatbot.repository;

import com.example.chatbot.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Custom query to find messages by sender
    List<Message> findBySender(String sender);

    // Custom query to find messages by recipient
    List<Message> findByRecipient(String recipient);

    // Custom query to retrieve recent messages
    List<Message> findTop10ByOrderByTimestampDesc();
}
