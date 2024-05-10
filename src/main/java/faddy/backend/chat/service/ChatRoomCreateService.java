package faddy.backend.chat.service;

import faddy.backend.chat.domain.ChatRoom;
import faddy.backend.chat.dto.command.ChatRoomCreateCommand;
import faddy.backend.chat.repository.ChatRoomJpaRepository;
import faddy.backend.chat.service.useCase.ChatRoomCreateUseCase;
import faddy.backend.global.exception.InternalServerException;
import faddy.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatRoomCreateService implements ChatRoomCreateUseCase {

    private static final String LOG_LOCATION = ChatRoomCreateService.class.getName();

    private final ChatRoomJpaRepository chatRoomRepository;
    private final UserService userService;

    @Override
    public Long createChatRoom(ChatRoomCreateCommand command) {


        //check validate userId and decode userId
        Long masterId = userService.getUserIdByEncrptedUserId(command.master());

        ChatRoom chatRoom = ChatRoom.createChatRoom(command.title(), masterId);

       try {
           ChatRoom savedRoom = chatRoomRepository.save(chatRoom);

           return savedRoom.getId();

       } catch (DataAccessException e) {
            log.warn(LOG_LOCATION + "[createChatRoom] : chatRoom을 저장하는데 실패했습니다.");
            throw new InternalServerException(e.getMessage() + LOG_LOCATION , e);

       } catch (Exception e) {
           log.warn(LOG_LOCATION + "[createChatRoom] : 알 수 없는 에러가 발생했습니다.", e);
           throw new InternalServerException(e.getMessage() + LOG_LOCATION , e);
       }


    }
}
