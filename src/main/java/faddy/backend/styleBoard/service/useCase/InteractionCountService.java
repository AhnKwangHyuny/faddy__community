package faddy.backend.styleBoard.service.useCase;

import faddy.backend.styleBoard.dto.response.InteractionCountDTO;

public interface InteractionCountService {

    /**
     *  styleBoardId에 해당하는 styleBoard의 interactionCount 조회 후 반환 - likeCount, viewCount, commentCount
     *  @param styleBoardId  조회할 styleBoard의 id
     *  @return InteractionCountDTO styleBoard의 interactionCount
     * */
    InteractionCountDTO getInteractionCount(Long styleBoardId);
}
