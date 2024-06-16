package faddy.backend.styleBoardComment.service;

import faddy.backend.global.exception.CommentNotFoundException;
import faddy.backend.styleBoard.service.useCase.StyleBoardDetailService;
import faddy.backend.styleBoardComment.dto.response.find.StyleBoardCommentResponseDTO;
import faddy.backend.styleBoardComment.repository.StyleBoardCommentJpaRepository;
import faddy.backend.styleBoardComment.service.useCase.GetStyleBoardCommentService;
import faddy.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class GetStyleBoardCommentServiceImpl implements GetStyleBoardCommentService {

    private final StyleBoardCommentJpaRepository styleBoardCommentRepository;

    private final StyleBoardDetailService styleBoardDetailService;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public List<StyleBoardCommentResponseDTO> findByStyleBoardIdWithReplies(Long styleBoardId) {
        try {
           // StyleBoard Comment 조회
            return styleBoardCommentRepository.findByStyleBoardIdWithChildren(styleBoardId)
                    .stream()
                    .map(StyleBoardCommentResponseDTO::from)
                    .toList();

        } catch (Exception e) {
            throw new CommentNotFoundException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
