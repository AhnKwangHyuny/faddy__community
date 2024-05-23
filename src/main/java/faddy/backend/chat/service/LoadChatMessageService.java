package faddy.backend.chat.service;

import faddy.backend.chat.domain.Chat;
import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.response.ChatMessageResponse;
import faddy.backend.chat.repository.ChatJpaRepository;
import faddy.backend.chat.service.useCase.ChatMessageLoadUseCase;
import faddy.backend.global.exception.ChatServiceException;
import faddy.backend.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class LoadChatMessageService implements ChatMessageLoadUseCase {

    private final ChatJpaRepository chatRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessageResponse> loadChatsByChatRoom(ChatRoom chatRoom) {
        try {
            List<Chat> chats = chatRepository.findByChatRoom(chatRoom);

            return chats.stream()
                    .map(chat -> ChatMessageResponse.builder()
                            .id(chat.getId())
                            .content(chat.getContent())
                            .sender(chat.getSenderId())
                            .type(chat.getType())
                            .createdAt(chat.getCreated_at())
                            .build())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to load chat messages", e);
        }
    }
}