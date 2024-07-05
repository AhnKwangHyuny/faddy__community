package faddy.backend.styleBoard.service;

import faddy.backend.global.exception.DeleteEntityException;
import faddy.backend.hashTags.service.HashTagService;
import faddy.backend.like.service.useCase.LikeService;
import faddy.backend.like.type.ContentType;
import faddy.backend.styleBoard.dto.response.CheckOwnerResponseDTO;
import faddy.backend.styleBoard.repository.StyleBoardJpaRepository;
import faddy.backend.styleBoard.service.useCase.InteractionCountService;
import faddy.backend.styleBoard.service.useCase.StyleBoardDeleteService;
import faddy.backend.styleBoard.service.useCase.StyleBoardDetailService;
import faddy.backend.styleBoardComment.service.useCase.StyleBoardCommentDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StyleBoardDeleteServiceImpl implements StyleBoardDeleteService {

    private final StyleBoardDetailService styleBoardDetailService;
    private final InteractionCountService interactionCountService;
    private final HashTagService hashTagService;
    private final StyleBoardCommentDeleteService styleBoardCommentDeleteService;
    private final LikeService likeService;

    private final StyleBoardJpaRepository styleBoardRepository;

    @Override
    public void deleteStyleBoard(String authorization , Long styleBoardId) {
        try {
            // check styleBoard owner
            CheckOwnerResponseDTO isOwner = styleBoardDetailService.checkStyleBoardOwner(styleBoardId, authorization);

            if (!isOwner.isOwner()) {
                throw new DeleteEntityException(HttpStatus.FORBIDDEN , "해당 스타일보드의 작성자가 아닙니다." , styleBoardId);
            }

            //delete styleBoard hashTags
            hashTagService.deleteHashTagsByStyleBoardId(styleBoardId);

            // delete styleBoardComment data & interaction count data
            styleBoardCommentDeleteService.deleteAllAndInteractionCountByStyleBoardId(styleBoardId);

            // delete likes
            likeService.deleteLike(ContentType.STYLE_BOARD , styleBoardId);

            //delete interaction count data
            interactionCountService.deleteStyleBoardInteractionCounts(styleBoardId);

            // delete styleBoard
            styleBoardRepository.deleteById(styleBoardId);

        } catch (Exception e) {
            throw new DeleteEntityException(HttpStatus.BAD_REQUEST , e.getMessage() ,styleBoardId);
        }
    }


}
