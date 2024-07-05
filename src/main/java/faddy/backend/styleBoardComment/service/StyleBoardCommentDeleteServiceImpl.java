package faddy.backend.styleBoardComment.service;

import faddy.backend.global.exception.DeleteEntityException;
import faddy.backend.styleBoardComment.repository.StyleBoardCommentJpaRepository;
import faddy.backend.styleBoardComment.service.useCase.StyleBoardCommentDeleteService;
import faddy.backend.styleBoardComment.service.useCase.StyleBoardCommentRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StyleBoardCommentDeleteServiceImpl implements StyleBoardCommentDeleteService {

    private final StyleBoardCommentJpaRepository styleBoardCommentRepository;
    private final StyleBoardCommentRedisService styleBoardCommentRedisService;

    @Override
    public void deleteAllAndInteractionCountByStyleBoardId(Long styleBoardId) {
        try {

            // delete interaction count data (추후 구현)

            // delete styleBoardComment data
            styleBoardCommentRepository.deleteAllByStyleBoardId(styleBoardId);


        } catch (Exception e) {
            log.error("deleteAllAndInteractionCountByStyleBoardId error : {}", e.getMessage());

            throw new DeleteEntityException(HttpStatus.BAD_REQUEST , e.getMessage() , styleBoardId);
        }
    }
}
