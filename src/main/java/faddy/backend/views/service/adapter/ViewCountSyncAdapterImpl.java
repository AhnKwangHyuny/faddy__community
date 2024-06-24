package faddy.backend.views.service.adapter;

import faddy.backend.global.exception.SaveEntityException;
import faddy.backend.styleBoard.domain.StyleBoard;
import faddy.backend.styleBoard.repository.StyleBoardJpaRepository;
import faddy.backend.views.service.adapter.useCase.ViewCountSyncAdapter;
import faddy.backend.views.service.useCase.ViewRedisService;
import faddy.backend.views.type.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewCountSyncAdapterImpl implements ViewCountSyncAdapter {

    private final ViewRedisService viewRedisService;
    private final StyleBoardJpaRepository styleBoardRepository;


    @Override
    @Transactional
    public void updateViewCountsInDatabase() {
        try {
            List<StyleBoard> styleBoards = styleBoardRepository.findAll();

            for (StyleBoard styleBoard : styleBoards) {

                Long styleBoardId = styleBoard.getId();
                int viewCount = viewRedisService.countViews(styleBoardId, ContentType.STYLE_BOARD);

                // 조회수 Redis에서 조회 및 DB 업데이트
                styleBoard.updateViewCount(viewCount);

                styleBoardRepository.save(styleBoard);
            }


        } catch (Exception e) {
            throw new SaveEntityException(HttpStatus.INTERNAL_SERVER_ERROR , e.getMessage());
        }

    }
}
