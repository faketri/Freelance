package com.hivework.domain.service.chat;

import com.hivework.domain.entity.messager.ChatRoom;
import com.hivework.domain.repository.ChatRoomRepository;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public Optional<String> getChatId(
            Long senderId, Long recipientId, boolean createIfNotExist) {

        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(!createIfNotExist) {
                        return  Optional.empty();
                    }
                    var chatId =
                            String.format("%s_%s", senderId, recipientId);

                    ChatRoom senderRecipient = new ChatRoom();
                    senderRecipient.setSenderId(senderId);
                    senderRecipient.setRecipientId(recipientId);

                    ChatRoom recipientSender = new ChatRoom();
                    recipientSender.setSenderId(senderId);
                    recipientSender.setRecipientId(recipientId);

                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }
}
