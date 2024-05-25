package faddy.backend.chat.service;

import faddy.backend.chat.domain.Chat;
import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.response.ChatMessageResponse;
import faddy.backend.chat.repository.ChatJpaRepository;
import faddy.backend.chat.service.useCase.ChatMessageLoadUseCase;
import faddy.backend.chat.type.ContentType;
import faddy.backend.global.exception.ChatServiceException;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.user.service.UserIdEncryptionUtil;
import faddy.backend.user.service.UserService;
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
    private final UserIdEncryptionUtil userIdEncryptionUtil;
    private final UserService userService;
    private final LoadChatRoomService loadChatRoomService;

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessageResponse> loadChatsByChatRoom(ChatRoom chatRoom) {
        try {
            List<Chat> chats = chatRepository.findByChatRoom(chatRoom);

            return chats.stream()
                    .map(chat -> ChatMessageResponse.builder()
                            .id(chat.getId())
                            .content(chat.getContent())
                            .sender(chat.getType() == ContentType.TIMESTAMP || chat.getType() == ContentType.SYSTEM
                                    ? "system"
                                    : userService.getUsernameByUserId(chat.getSenderId()))
                            .type(chat.getType())
                            .createdAt(chat.getCreated_at())
                            .build())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to load chat messages", e);
        }
    }

    /**
     * 해당 room에서 가장 최신 chat message를 조회
     *
     * @param roomId      chatRoom id
     * @param allowedTypes 조회할 chat message의 type
     *                     (ex. TEXT, IMAGE, SYSTEM)
     * @return Chat
     */
    @Override
    @Transactional(readOnly = true)
    public Chat loadLastChatMessage(Long roomId, List<ContentType> allowedTypes) {
        // 해당 chatRoom 엔티티 조회
        ChatRoom room = loadChatRoomService.getChatRoomById(roomId);

        // 해당 chatRoom의 마지막 chat message 조회
        return chatRepository.findTopByChatRoomOrderByCreatedAtDesc(room, allowedTypes)
                .orElse(null);
    }
}
