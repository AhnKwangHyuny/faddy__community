package faddy.backend.styleBoard.service.useCase;

import faddy.backend.styleBoard.domain.StyleBoard;
import faddy.backend.styleBoard.dto.response.CheckOwnerResponseDTO;
import faddy.backend.styleBoard.dto.response.StyleBoardDetailResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface StyleBoardDetailService {
    StyleBoardDetailResponseDTO getStyleBoardDetailData(Long styleBoardId, String category , Long viewerId);

    /**
     *  styleBoard detail에 접근한 유저가 해당 detail 작성자인지 확인
     *  @param styleBoardId
     *  @param String token
     *  @param Long viewerId detail 접근하는 유저
     *  @return CheckOwnerResponseDTO {isOwner: boolean}
     * */
    CheckOwnerResponseDTO checkStyleBoardOwner(Long styleBoardId, String token);


    /**
     *  styleBoard 조회 후 반환
     *  @param styleBoardId
     *  @return styleBoard styleBoard 엔티티
     * */
    StyleBoard getStyleBoard(Long styleBoardId);

}