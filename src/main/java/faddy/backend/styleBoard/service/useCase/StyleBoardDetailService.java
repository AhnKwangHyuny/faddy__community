package faddy.backend.styleBoard.service.useCase;

import faddy.backend.styleBoard.dto.response.CheckOwnerResponseDTO;
import faddy.backend.styleBoard.dto.response.StyleBoardDetailResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface StyleBoardDetailService {
    StyleBoardDetailResponseDTO getStyleBoardDetailData(Long styleBoardId, String category);

    /**
     *  styleBoard detail에 접근한 유저가 해당 detail 작성자인지 확인
     *  @param styleBoardId
     *  @param String token
     *  @return CheckOwnerResponseDTO {isOwner: boolean}
     * */
    CheckOwnerResponseDTO checkStyleBoardOwner(Long styleBoardId, String token);

}