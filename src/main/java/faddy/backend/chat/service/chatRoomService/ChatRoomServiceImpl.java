package faddy.backend.chat.service.chatRoomService;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.request.UpdateChatRoomRequest;
import faddy.backend.chat.repository.ChatRoomJpaRepository;
import faddy.backend.chat.repository.ChatRoomUserJpaRepository;
import faddy.backend.global.exception.ChatServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomJpaRepository chatRoomRepository;
    private final ChatRoomUserJpaRepository chatRoomUserRepository;
    @Override
    public void updateChatRoom(Long roomId, UpdateChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new ChatServiceException(HttpStatus.BAD_REQUEST.value(),
                        ChatRoomServiceImpl.class.getName() + "채팅방을 찾을 수 없습니다."));

        chatRoom.updateChatRoom(request.getTitle());
    }

    @Override
    @Transactional(rollbackFor = {ChatServiceException.class, RuntimeException.class})
    public void deleteChatRoom(Long roomId) {
        try {
            ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                    .orElseThrow(() -> new ChatServiceException(HttpStatus.BAD_REQUEST.value(),
                            "채팅방을 찾을 수 없습니다."));

            chatRoomRepository.delete(chatRoom);

            // ChatRoom과 관련된 ChatRoomUser 모두 삭제
            chatRoomUserRepository.deleteChatRoomUsersByChatRoomId(roomId);


        } catch (Exception e) {
            // Optionally log the exception
            log.error("Failed to delete chat room", e);

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            throw e;
        }

    }
}
