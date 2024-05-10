package faddy.backend.chat.dto.command;

import lombok.Builder;

@Builder
public record ChatRoomCreateCommand(String title , String master) {
}
