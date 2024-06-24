package faddy.backend.styleBoard.dto.response;

import faddy.backend.profile.domain.UserLevel;
import faddy.backend.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO {
    private UserLevel level;
    private String profileImageUrl;
    private String nickname;

    public static UserProfileDTO from(User user) {
        return UserProfileDTO.builder()
                .level(user.getProfile().getUserLevel())
                .profileImageUrl(user.getProfile().getProfileImageUrl())
                .nickname(user.getNickname())
                .build();
    }
}