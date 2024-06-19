package faddy.backend.styleBoard.service;

import faddy.backend.global.exception.StyleBoardDataAccessException;
import faddy.backend.global.exception.StyleBoardNotFoundException;
import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.hashTags.service.HashTagService;
import faddy.backend.image.service.ImageService;
import faddy.backend.like.service.useCase.LikeRedisService;
import faddy.backend.like.type.ContentType;
import faddy.backend.log.exception.ExceptionLogger;
import faddy.backend.styleBoard.domain.StyleBoard;
import faddy.backend.styleBoard.dto.response.CheckOwnerResponseDTO;
import faddy.backend.styleBoard.dto.response.StyleBoardDetailResponseDTO;
import faddy.backend.styleBoard.repository.StyleBoardJpaRepository;
import faddy.backend.styleBoard.service.useCase.StyleBoardDetailService;
import faddy.backend.user.domain.Profile;
import faddy.backend.user.domain.User;
import faddy.backend.user.service.UserService;
import faddy.backend.views.service.useCase.ViewRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StyleBoardDetailServiceImpl implements StyleBoardDetailService {

    private final ImageService imageService;
    private final UserService userService;
    private final HashTagService hashTagService;
    private final LikeRedisService likeRedisService;
    private final ViewRedisService viewRedisService;

    private final StyleBoardJpaRepository styleBoardRepository;

    private final String FAIL_GET_STYLE_BOARD = "[error] 스타일보드 조회에 실패했습니다.";

    @Override
    @Transactional(readOnly = true)
    public StyleBoardDetailResponseDTO getStyleBoardDetailData(Long styleBoardId, String category , Long viewerId) {
        try {
            // StyleBoard 조회
            StyleBoard styleBoard = styleBoardRepository.findByIdWithAuthorAndProfile(styleBoardId)
                    .orElseThrow(() -> new StyleBoardNotFoundException(styleBoardId));

            // HashTags 조회
            List<HashTag> hashTagsByStyleBoardId = hashTagService.findHashTagsByStyleBoardId(styleBoardId);

            // HashTags 리스트를 String 리스트로 변환
            List<String> hashTagList = hashTagsByStyleBoardId.isEmpty() ? List.of() :
                    hashTagsByStyleBoardId.stream().map(HashTag::getName).collect(Collectors.toList());

            // Author와 Profile 정보 조회
            User author = styleBoard.getAuthor();
            Profile profile = author.getProfile();

            //like 조회
            ContentType type = ContentType.STYLE_BOARD;
            int likeCount = likeRedisService.countLikes(styleBoardId, type);
            boolean isLiked = likeRedisService.isLiked(styleBoardId, author.getId(), type);

            //view 조회
            faddy.backend.views.type.ContentType contentType = faddy.backend.views.type.ContentType.STYLE_BOARD;
            viewRedisService.saveView(styleBoardId,viewerId, contentType);
            int viewCount = viewRedisService.countViews(styleBoardId, contentType);


            // DTO 생성 및 반환
            return StyleBoardDetailResponseDTO.builder()
                    .title(styleBoard.getTitle())
                    .content(styleBoard.getContent())
                    .category(styleBoard.getCategory().toString())
                    .createdAt(styleBoard.getCreated_at())
                    .nickname(author.getNickname())
                    .userLevel(profile.getUserLevel())
                    .profileImageUrl(profile.getProfileImageUrl())
                    .hashTags(hashTagList)
                    .likeCount(likeCount)
                    .isLiked(isLiked)
                    .viewCount(viewCount)
                    .build();

        } catch (Exception e) {
            ExceptionLogger.logException(e);
            throw new StyleBoardDataAccessException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CheckOwnerResponseDTO checkStyleBoardOwner(Long styleBoardId, String token) {
        try {
            // 로그인된 접근 유저 토큰을 통해 유저 entity 조회
            User user = userService.findUserByToken(token);

            // 조회된 유저 id를 통해 styleBoard user id와 동일한지 확인
            boolean isOwner = styleBoardRepository.existsByIdAndUserId(styleBoardId, user.getId());

            return new CheckOwnerResponseDTO(isOwner);

        } catch (Exception e) {
            ExceptionLogger.logException(e);
            throw new StyleBoardDataAccessException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public StyleBoard getStyleBoard(Long styleBoardId) {
        try {
            return styleBoardRepository.findById(styleBoardId)
                    .orElseThrow(() -> new StyleBoardNotFoundException(styleBoardId));
        } catch (Exception e) {
            throw new StyleBoardDataAccessException(HttpStatus.BAD_REQUEST.value(), FAIL_GET_STYLE_BOARD);
        }
    }
}
