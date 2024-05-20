package faddy.backend.chat.service.chatRoomUserService;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.domain.ChatRoomUser;
import faddy.backend.chat.repository.ChatRoomJpaRepository;
import faddy.backend.chat.repository.ChatRoomUserJpaRepository;
import faddy.backend.global.exception.AuthorizationException;
import faddy.backend.global.exception.ChatRoomException;
import faddy.backend.user.domain.User;
import faddy.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ChatRoomUserServiceImpl implements ChatRoomUserService {

    private final UserService userService;
    private final ChatRoomUserJpaRepository chatRoomUserRepository;
    private final ChatRoomJpaRepository chatRoomRepository;

    private final String ERROR_ROOT = ChatRoomUserServiceImpl.class.getName();

    @Override
    public void addUserToChatRoom(ChatRoom room, List<String> memberIds) {
        List<Long> decryptedMemberIds = memberIds.stream()
                .map(userService::decryptUserId)
                .toList();
        try {
            for (Long memberId : decryptedMemberIds) {
                User user = userService.findUserById(memberId);
                ChatRoomUser chatRoomUser = new ChatRoomUser(user, room);
                chatRoomUserRepository.save(chatRoomUser);
            }
        } catch (Exception e) {
            log.error(ChatRoomUserServiceImpl.class.getName()+ " Failed to add user to chat room", e);
            throw e;
        }

    }


    @Override
    public void removeUserFromChatRoom(String userId, Long roomId) {
        try {
            Long decryptedUserId = userService.decryptUserId(userId);
            chatRoomUserRepository.deleteByUserIdAndChatRoomId(decryptedUserId, roomId);

        } catch (AuthorizationException e) {
            log.error(UserService.class.getName() + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(ChatRoomUserServiceImpl.class.getName() + " Failed to remove user from chat room", e);
            throw e;
        }

    }

    @Override
    @Transactional
    public void inviteUserToChatRoom(List<String> users, Long roomId) {

        //users가 비었다면 예외처리
        if (users.isEmpty()) {
            log.error(ChatRoomUserServiceImpl.class.getName() + "users is empty");
            throw new AuthorizationException(HttpStatus.BAD_REQUEST.value(),"초대할 대화상대가 존재하지 않습니다.");
        }

        List<Long> decryptedUserIds = users.stream()
                .map(userService::decryptUserId)
                .toList();

        try {
            ChatRoom room = chatRoomRepository.findById(roomId)
                    .orElseThrow(()-> new ChatRoomException(HttpStatus.BAD_REQUEST.value(),
                            ERROR_ROOT+ " 채팅방을 찾을 수 없습니다."));

            for (Long userId : decryptedUserIds) {
                User user = userService.findUserById(userId);
                ChatRoomUser chatRoomUser = new ChatRoomUser(user, room);
                chatRoomUserRepository.save(chatRoomUser);
            }
        } catch (Exception e) {
            log.error(ChatRoomUserServiceImpl.class.getName() + " Failed to invite user to chat room", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
    }
}
