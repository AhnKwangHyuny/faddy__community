package faddy.backend.styleBoard.service.useCase;


import faddy.backend.styleBoard.dto.request.StyleBoardEditRequestDTO;
import org.springframework.transaction.annotation.Transactional;

public interface StyleBoardEditService {

    @Transactional
    void updateStyleBoard(StyleBoardEditRequestDTO request , Long styleBoardId);
}