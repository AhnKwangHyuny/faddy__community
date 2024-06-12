package faddy.backend.styleBoard.service.useCase;

import faddy.backend.styleBoard.domain.StyleBoard;
import faddy.backend.styleBoard.dto.request.StyleBoardCreateDTO;

public interface StyleBoardCreateService {

    /**
     *  스타일보드 엔팉  생성
     *  @param StyleBoardCreateDTO 스타일보드 생성 요청 DTO (title , content , category , hashTags)
     *  @return StyleBoard 스타일보드 엔티티
     * */
    StyleBoard createStyleBoardEntity(StyleBoardCreateDTO request);

}
