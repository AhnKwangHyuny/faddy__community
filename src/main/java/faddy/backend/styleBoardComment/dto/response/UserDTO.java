package faddy.backend.styleBoardComment.dto.response;

import faddy.backend.profile.domain.UserLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private UserLevel level;
    private String profileImageUrl;
    private String nickname;
}