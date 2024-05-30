package faddy.backend.chat.dto.response;


import faddy.backend.chat.dto.LastChatContentDto;
import faddy.backend.chat.type.ChatRoomType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ChatRoomResponse(Long roomId , String title , String thumbnailImage , LastChatContentDto chatContentDto, int roomMemberCount , String createdAt , ChatRoomType type) {
}
