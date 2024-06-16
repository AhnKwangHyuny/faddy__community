package faddy.backend.styleBoardComment.service.useCase;

import faddy.backend.styleBoardComment.dto.request.StyleBoardCommentCreateRequestDTO;
import faddy.backend.styleBoardComment.dto.response.StyleBoardCommentCreateResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface CreateStyleBoardCommentCreateService {
    StyleBoardCommentCreateResponseDTO create(StyleBoardCommentCreateRequestDTO commentDto , HttpServletRequest request , Long styleBoardId);
}
