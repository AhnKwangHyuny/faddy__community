package faddy.backend.styleBoardComment.service;

import faddy.backend.global.exception.SaveEntityException;
import faddy.backend.styleBoard.domain.StyleBoard;
import faddy.backend.styleBoard.service.useCase.StyleBoardDetailService;
import faddy.backend.styleBoardComment.domain.StyleBoardComment;
import faddy.backend.styleBoardComment.dto.request.StyleBoardCommentCreateRequestDTO;
import faddy.backend.styleBoardComment.dto.response.StyleBoardCommentCreateResponseDTO;
import faddy.backend.styleBoardComment.repository.StyleBoardCommentJpaRepository;
import faddy.backend.styleBoardComment.service.useCase.CreateStyleBoardCommentCreateService;
import faddy.backend.styleBoardComment.utils.StyleBoardCommentMapper;
import faddy.backend.user.domain.User;
import faddy.backend.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CreateStyleBoardCommentCreateServiceImpl implements CreateStyleBoardCommentCreateService {

    private final StyleBoardCommentJpaRepository styleBoardCommentRepository;


    private final StyleBoardDetailService styleBoardDetailService;
    private final UserService userService;


    @Override
    public StyleBoardCommentCreateResponseDTO create(StyleBoardCommentCreateRequestDTO commentDto ,
                                                     HttpServletRequest request , Long styleBoardId) {
        try {
            // StyleBoard 조회 후 댓글 생성 (동시성 , 정합성)
            StyleBoard styleBoard = styleBoardDetailService.getStyleBoard(styleBoardId);

            // 유저 조회
            String token = request.getHeader("Authorization");
            User author = userService.findUserByToken(token);

            //comment 생성
            StyleBoardComment comment = StyleBoardComment.builder()
                    .content(commentDto.getContent())
                    .styleBoard(styleBoard)
                    .author(author)
                    .build();

            //comment 저장
            StyleBoardComment savedComment = styleBoardCommentRepository.save(comment);

            // comment to response
            return StyleBoardCommentMapper.toDto(savedComment);

        } catch (Exception e) {
            throw new SaveEntityException(HttpStatus.INTERNAL_SERVER_ERROR , e.getMessage());
        }
    }
}
