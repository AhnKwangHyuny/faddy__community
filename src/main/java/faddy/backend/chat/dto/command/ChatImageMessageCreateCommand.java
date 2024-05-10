package faddy.backend.chat.dto.command;

import faddy.backend.chat.type.ContentType;
import lombok.Builder;

@Builder
public record ChatImageMessageCreateCommand(Long roomId , String content , Long sender , ContentType type) {

}
