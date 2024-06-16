package faddy.backend.styleBoardComment.dto.response.find;

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
public class UserDTO {
    private UserLevel level;
    private String profileImageUrl;
    private String nickname;

    public static UserDTO from(User user) {
        return UserDTO.builder()
                .level(user.getProfile().getUserLevel())
                .profileImageUrl(user.getProfile().getProfileImageUrl())
                .nickname(user.getNickname())
                .build();
    }
}