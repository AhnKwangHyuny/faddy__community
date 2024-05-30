package faddy.backend.chat.service.adapter;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.request.CreateChatRoomRequest;
import faddy.backend.chat.dto.response.CreateChatRoomResponse;
import faddy.backend.chat.service.ChatRoomCreateService;
import faddy.backend.chat.service.chatRoomUserService.ChatRoomUserService;
import faddy.backend.global.exception.ChatRoomException;
import faddy.backend.log.exception.ExceptionLogger;
import faddy.backend.user.repository.UserRepository;
import faddy.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatRoomCreatePersistenceAdapter {

    private final ChatRoomCreateService chatRoomCreateService;
    private final ChatRoomUserService chatRoomUserService;
    private final UserRepository userRepository;
    private final UserService userService;

    @Transactional
    public CreateChatRoomResponse createChatRoom(CreateChatRoomRequest request) {

        try {
            ChatRoom room = chatRoomCreateService.createChatRoom(request);


            List<Long> decryptedMemberIds = decryptMemberIds(request.getMasterId(),request.getMemberIds());
            String defaultTitle = generateChatRoomTitle(decryptedMemberIds);

            room.changeTitle(defaultTitle);
            chatRoomUserService.addUserToChatRoom(room, decryptedMemberIds);

            return CreateChatRoomResponse.builder().roomId(room.getId()).build();
        } catch (Exception e) {
            ExceptionLogger.logException(e);
            throw new ChatRoomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "채팅방 생성에 실패했습니다.");
        }
    }

    /**
     *  채팅방 맴버들 아이디 복호화
     *  @param String masterId 대화방 생성자 아이디
     *  @param List<String> encryptedMemberIds 암호화된 대화 맴버 ids
     *  @return List<Long> 복호화된 대화 맴버 ids
     * */
    private List<Long> decryptMemberIds(String masterId , List<String> encryptedMemberIds) {

        List<String> memberIds = new ArrayList<>(encryptedMemberIds);
        memberIds.add(masterId);

        List<Long> decryptedMemberIds = new ArrayList<>(userService.decryptUserIds(memberIds));
        Collections.reverse(decryptedMemberIds);

        return decryptedMemberIds;
    }

    /**
     *  대화 맴버 아이디로 이우러진 기본 타이틀 생선
     *  @param decryptedMemberIds 복호화된 대화 맴버 ids
     *  @return 대화방 타이틀
     * */
    private String generateChatRoomTitle(List<Long> decryptedMemberIds) {
        List<String> nicknameList = userRepository.findNicknamesByUserIds(decryptedMemberIds);
        return String.join(", ", nicknameList);
    }
}
