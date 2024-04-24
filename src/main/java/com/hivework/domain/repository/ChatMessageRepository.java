package com.hivework.domain.repository;

import com.hivework.domain.entity.messager.ChatMessage;
import com.hivework.domain.entity.messager.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    Optional<ChatMessage> findBySenderId(Long senderId);

    long countBySenderIdAndRecipientIdAndStatus(Long senderId, Long recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

    Optional<ChatMessage> findBySenderIdAndRecipientIdAndStatus(Long senderId, Long recipientId, MessageStatus status);

    Optional<ChatMessage> getBySenderIdAndRecipientId(Long senderId, Long recipientId);
}