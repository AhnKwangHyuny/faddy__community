package faddy.backend.styleBoard.service.useCase;

import faddy.backend.styleBoard.domain.StyleBoard;

import java.util.List;

public interface StyleBoardLoadService {

    /**
     *  서버에 저장된 모든 styleBoard 조회 후 반환
     *  @return list<StyleBoard> styleBoard 리스트
     * */
    List<StyleBoard> getAll();

}
