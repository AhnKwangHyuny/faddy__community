package faddy.backend.styleBoard.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleBoardResponseDTO {

    private String title;

    private LocalDateTime createdAt;

    private UserProfileDTO userProfileDTO;

    private InteractionCountDTO interactionCountDTO;


}
