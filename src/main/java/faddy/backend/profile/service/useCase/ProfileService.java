package faddy.backend.profile.service.useCase;

import faddy.backend.user.domain.Profile;

public interface ProfileService {
    Profile findProfileByUserid(Long userId);

    String findProfileImageUrlByUserId(Long userId);

}
