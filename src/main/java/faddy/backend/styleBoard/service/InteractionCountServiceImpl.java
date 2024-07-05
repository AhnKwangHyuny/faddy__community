package faddy.backend.styleBoard.service;

import faddy.backend.global.exception.DeleteEntityException;
import faddy.backend.like.service.useCase.LikeRedisService;
import faddy.backend.like.type.ContentType;
import faddy.backend.styleBoard.dto.response.InteractionCountDTO;
import faddy.backend.styleBoard.service.useCase.InteractionCountService;
import faddy.backend.styleBoardComment.service.useCase.StyleBoardCommentRedisService;
import faddy.backend.views.service.useCase.ViewRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class InteractionCountServiceImpl implements InteractionCountService {

    private final ViewRedisService viewRedisService;
    private final LikeRedisService likeRedisService;
    private final StyleBoardCommentRedisService styleBoardCommentRedisService;

    @Override
    @Transactional(readOnly = true)
    public InteractionCountDTO getInteractionCount(Long styleBoardId) {

        ContentType LikeContentType = ContentType.STYLE_BOARD;
        faddy.backend.views.type.ContentType ViewContentType = faddy.backend.views.type.ContentType.STYLE_BOARD;

        //각각의 count 조회
        int viewCount = viewRedisService.countViews(styleBoardId, ViewContentType);
        int likeCount = likeRedisService.countLikes(styleBoardId, LikeContentType);
        int commentCount = styleBoardCommentRedisService.countStyleBoardComments(styleBoardId);

        return InteractionCountDTO.builder()
                .viewCount(viewCount)
                .likeCount(likeCount)
                .commentCount(commentCount)
                .build();
    }

    @Override
    @Transactional
    public void deleteStyleBoardInteractionCounts(Long styleBoardId) {
        try {

            ContentType likeContentType = ContentType.STYLE_BOARD;
            faddy.backend.views.type.ContentType viewContentType = faddy.backend.views.type.ContentType.STYLE_BOARD;

            likeRedisService.deleteLikes(styleBoardId, likeContentType);
            viewRedisService.deleteViews(styleBoardId, viewContentType);
            styleBoardCommentRedisService.deleteStyleBoardComments(styleBoardId);

        } catch (Exception e) {
            throw new DeleteEntityException(HttpStatus.BAD_REQUEST, e.getMessage(), styleBoardId);
        }
    }
}
