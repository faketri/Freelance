package com.hivework.domain.service.chat;

import com.hivework.domain.entity.messager.ChatMessage;
import com.hivework.domain.entity.messager.MessageStatus;
import com.hivework.domain.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public long countNewMessages(Long senderId, Long recipientId) {
        return chatMessageRepository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<ChatMessage> findChatMessages(Long senderId, Long recipientId) {
        String chatId = chatMessageRepository
                .getBySenderIdAndRecipientId(senderId, recipientId)
                .orElseThrow(
                        () -> new RuntimeException("Not found")
                )
                .getChatId();

        var messages = chatMessageRepository.findByChatId(chatId);

        if (!messages.isEmpty()) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        return messages;
    }

    public ChatMessage findById(Long id) {
        return chatMessageRepository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return chatMessageRepository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new RuntimeException("can't find message (" + id + ")"));
    }

    public void updateStatuses(Long senderId, Long recipientId, MessageStatus status) {
        var chatMessage = chatMessageRepository
                .findBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.DELIVERED)
                .orElse(null);

        if (chatMessage == null)
            return;

        chatMessage.setStatus(status);
        chatMessageRepository.save(chatMessage);
    }

    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }
}
