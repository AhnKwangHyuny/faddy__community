package faddy.backend.styleBoard.service.adapter;

import faddy.backend.global.exception.SaveEntityException;
import faddy.backend.hashTags.domain.HashTag;
import faddy.backend.hashTags.dto.request.HashTagRequestDTO;
import faddy.backend.hashTags.service.HashTagService;
import faddy.backend.image.domain.Image;
import faddy.backend.image.dto.request.ImageLookupRequestDTO;
import faddy.backend.image.service.ImageService;
import faddy.backend.like.service.useCase.LikeRedisService;
import faddy.backend.like.type.ContentType;
import faddy.backend.log.exception.ExceptionLogger;
import faddy.backend.styleBoard.domain.StyleBoard;
import faddy.backend.styleBoard.dto.request.StyleBoardCreateDTO;
import faddy.backend.styleBoard.dto.request.StyleBoardRequestDTO;
import faddy.backend.styleBoard.repository.StyleBoardJpaRepository;
import faddy.backend.styleBoard.service.adapter.useCase.StyleBoardCreatePersistenceAdaptor;
import faddy.backend.styleBoard.service.useCase.StyleBoardCreateService;
import faddy.backend.styleBoard.utils.StyleBoardRequestParser;
import faddy.backend.user.domain.User;
import faddy.backend.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StyleBoardCreatePersistenceAdaptorImpl implements StyleBoardCreatePersistenceAdaptor {

    private final UserService userService;
    private final StyleBoardCreateService styleBoardCreateService;
    private final ImageService imageService;
    private final HashTagService hashTagService;
    private final LikeRedisService likeRedisService;

    private final StyleBoardJpaRepository styleBoardRepository;

    @Override
    public Long create(StyleBoardRequestDTO styleBoardRequestDTO , HttpServletRequest request) {

        try {

            // 작성자 조회
            User authorization = userService.findUserByToken(request.getHeader("Authorization"));

            log.info("Authorization user: {}", authorization);

            //styleBoard Entity 생성
            StyleBoardCreateDTO styleBoardCreateDTO = StyleBoardRequestParser.toStyleBoardCreateDTO(styleBoardRequestDTO);
            StyleBoard styleBoard = styleBoardCreateService.createStyleBoardEntity(styleBoardCreateDTO);

            //Image 조회
            List<Image> images = findImages(styleBoardRequestDTO);


            //HashTag 엔티티 생성
            List<HashTag> hashTags = saveHashTags(styleBoardRequestDTO);

            //연관관계 매핑
            associate(styleBoard, images, hashTags, authorization);

            //styleBoard 저장 (다른 연관관계 dirty checking)
            StyleBoard saved = styleBoardRepository.save(styleBoard);

            //styleBoard like 초기화
            likeRedisService.initializeLikes(saved.getId(), ContentType.STYLE_BOARD);

            return saved.getId();

        } catch (Exception e) {
            ExceptionLogger.logException(e);
            throw new SaveEntityException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "StyleBoard Entity 생성 실패", e);
        }
    }

    private void associate(StyleBoard styleBoard, List<Image> images, List<HashTag> hashTags, User user) {
        if (images != null) {
            for (Image image : images) {
                image.linkStyleBoard(styleBoard);
            }
        }

        if (hashTags != null) {
            for (HashTag hashTag : hashTags) {
                hashTag.linkToStyleBoard(styleBoard);
            }
        }

        styleBoard.linkToAuthor(user);
    }

    private List<Image> findImages(StyleBoardRequestDTO styleBoardRequestDTO) {
        List<ImageLookupRequestDTO> imageUrls = StyleBoardRequestParser.extractImageLookupRequestDTOs(styleBoardRequestDTO.getContent());
        return imageService.findByImageUrl(imageUrls);
    }

    private List<HashTag> saveHashTags(StyleBoardRequestDTO styleBoardRequestDTO) {
        List<HashTagRequestDTO> hashTagDtos = StyleBoardRequestParser.extractHashTagRequestDTOs(styleBoardRequestDTO.getHashTags());
        return hashTagService.saveHashTags(hashTagDtos);
    }
}
