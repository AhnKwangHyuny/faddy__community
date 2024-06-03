package faddy.backend.profile.domain.dto.response;

import faddy.backend.profile.domain.UserLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserProfileDTO {
    private String username;
    private String nickname;
    private String profileImageUrl;
    private UserLevel level;
}
