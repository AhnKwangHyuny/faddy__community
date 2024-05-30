package faddy.backend.profile.service;

import faddy.backend.profile.repository.ProfileJpaRepository;
import faddy.backend.profile.service.useCase.ProfileService;
import faddy.backend.user.domain.Profile;
import faddy.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileJpaRepository profileRepository;
    private final UserRepository userRepository;

    private final String DEFAULT_PROFILE_IMAGE_URL = "/default_profile_image.png";

    @Override
    @Transactional(readOnly = true)
    public Profile findProfileByUserid(Long userId) { // 추후 수정 예정
        return userRepository.findProfileById(userId)
                .orElse(null);

    }

    @Override
    public String findProfileImageUrlByUserId(Long userId) {

        Profile profile = findProfileByUserid(userId);

        // profile이 null이 아니라면 이미지 url을 반환
        if (profile != null) {
            return profile.getProfileImageUrl();
        }

        return DEFAULT_PROFILE_IMAGE_URL;
    }
}
