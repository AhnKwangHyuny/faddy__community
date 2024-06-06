package faddy.backend.snap.service;

import faddy.backend.category.domain.Category;
import faddy.backend.category.service.CategoryService;
import faddy.backend.global.Utils.EncryptionUtils;
import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.hashTags.service.TagService;
import faddy.backend.image.domain.Image;
import faddy.backend.image.service.ImageService;
import faddy.backend.snap.domain.Snap;
import faddy.backend.snap.domain.dto.request.CreateSnapRequestDto;
import faddy.backend.snap.repository.SnapRepository;
import faddy.backend.user.domain.User;
import faddy.backend.user.service.UserIdEncryptionUtil;
import faddy.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SnapPostingService {

    private final UserService userService;
    private final ImageService imageService;
    private final CategoryService categoryService;
    private final TagService hashTagService;
    private final UserIdEncryptionUtil userIdEncryptionUtil;

    private final SnapRepository snapRepository;

    @Transactional
    public String createSnap(CreateSnapRequestDto requestDto) throws Exception {

        // userId로 해당 user entity 찾기
        Long userId = userIdEncryptionUtil.decryptUserId(requestDto.getUserId());
        System.out.println("userId = " + userId);

        User writer = userService.getUserById(userId);

        //snap 연관관계 연결을 위한 HashTag entities
        List<Long> hashTagIds = requestDto.getHashTagIds();
        List<HashTag> hashTags = hashTagService.findHashTagsByIds(hashTagIds);

        //snap 연관관계 연결을 위한 Category entities
        List<Long> categoryIds = requestDto.getCategoryIds().getCategoryIdList();
        List<Category> categories = categoryService.findByIdsIn(categoryIds);

        String description = requestDto.getDescription();

        //snap 연관관계 연결을 위한 Image entities
        List<String> imageHashedNames = requestDto.getImageHashedNames();
        List<Image> images = imageService.findByHashedNames(imageHashedNames);

        // snap entity 생성
        Snap snap = new Snap(writer, images, hashTags, description);

        // snap : category = M : N
        for (Category category : categories) {
            snap.addCategory(category);
        }

        // user : snap = 1 : N
        writer.addSnap(snap);

        // snap : image = 1 : N
        images.forEach(image -> image.addSnap(snap));

        // concurrentModfiy error 우회 하기 위해 클래스 필드 값인 컬랙션 객체는 한 번만 수정
        snap.getSnapImages().addAll(images);

        // snap : hashTag = 1 : N
        hashTags.forEach(hashTag -> hashTag.linkToSnap(snap));
        snap.getHashTags().addAll(hashTags);

        Snap savedSnap = snapRepository.save(snap);

        //저장된 snap id token화
        String snapToken = EncryptionUtils.encryptEntityId(savedSnap.getId());

        return snapToken;
    }

}
