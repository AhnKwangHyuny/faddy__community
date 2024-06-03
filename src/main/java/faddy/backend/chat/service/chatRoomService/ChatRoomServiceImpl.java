package faddy.backend.chat.service.chatRoomService;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.LastChatContentDto;
import faddy.backend.chat.dto.request.UpdateChatRoomRequest;
import faddy.backend.chat.dto.response.ChatRoomResponse;
import faddy.backend.chat.repository.ChatRoomJpaRepository;
import faddy.backend.chat.repository.ChatRoomUserJpaRepository;
import faddy.backend.chat.service.LoadChatMessageService;
import faddy.backend.global.Utils.DateUtils;
import faddy.backend.global.exception.ChatRoomException;
import faddy.backend.global.exception.ChatServiceException;
import faddy.backend.log.exception.ExceptionLogger;
import faddy.backend.profile.service.useCase.UserProfileService;
import faddy.backend.user.service.UserService;
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
    private final LoadChatMessageService loadChatMessageService;
    private final UserService userService;
    private final UserProfileService profileService;
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

    @Override
    @Transactional(readOnly = true)
    public ChatRoomResponse mapToChatRoomResponse(ChatRoom room) {

        try {
            LastChatContentDto lastChatContentDto = loadChatMessageService.loadLastChatMessage(room.getId());

            //(임시) thumbnail image는 master user profile image (추후 변경 예정)
            String profileImage = profileService.findProfileImageUrlByUserId(room.getMasterId());

            //채팅방 인원 수 조회
            int userCount = chatRoomUserRepository.countByChatRoomId(room.getId());

            //채팅방 생성 시간 포멧 변경
            String createdAt = DateUtils.convertLocalDateTimeToChatTimeFormat(room.getCreated_at());


            return ChatRoomResponse.builder()
                    .roomId(room.getId())
                    .title(room.getTitle())
                    .thumbnailImage(profileImage)
                    .chatContentDto(lastChatContentDto)
                    .roomMemberCount(userCount)
                    .createdAt(createdAt)
                    .type(room.getType())
                    .build();

        } catch (Exception e) {
            ExceptionLogger.logException(e);

            throw new ChatRoomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }
}
