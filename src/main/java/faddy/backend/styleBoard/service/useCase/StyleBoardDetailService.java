package faddy.backend.styleBoard.service.useCase;

import faddy.backend.styleBoard.dto.response.StyleBoardDetailResponseDTO;

public interface StyleBoardDetailService {
    StyleBoardDetailResponseDTO getStyleBoardDetailData(Long styleBoardId, String category);
}