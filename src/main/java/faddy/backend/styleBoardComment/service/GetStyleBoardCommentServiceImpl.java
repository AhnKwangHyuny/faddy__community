package faddy.backend.styleBoardComment.service;

import faddy.backend.global.exception.CommentNotFoundException;
import faddy.backend.like.service.useCase.LikeRedisService;
import faddy.backend.like.type.ContentType;
import faddy.backend.styleBoard.service.useCase.StyleBoardDetailService;
import faddy.backend.styleBoardComment.domain.StyleBoardComment;
import faddy.backend.styleBoardComment.dto.response.find.StyleBoardCommentResponseDTO;
import faddy.backend.styleBoardComment.dto.response.find.StyleBoardReplyResponseDTO;
import faddy.backend.styleBoardComment.repository.StyleBoardCommentJpaRepository;
import faddy.backend.styleBoardComment.service.useCase.GetStyleBoardCommentService;
import faddy.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class GetStyleBoardCommentServiceImpl implements GetStyleBoardCommentService {

    private final StyleBoardCommentJpaRepository styleBoardCommentRepository;

    private final StyleBoardDetailService styleBoardDetailService;
    private final UserService userService;
    private final LikeRedisService likeRedisService;

    @Override
    @Transactional(readOnly = true)
    public List<StyleBoardCommentResponseDTO> findByStyleBoardIdWithReplies(Long styleBoardId) {
        try {
            List<StyleBoardComment> comments = styleBoardCommentRepository.findByStyleBoardIdWithChildren(styleBoardId);
            List<StyleBoardCommentResponseDTO> response = new ArrayList<>();

            for (StyleBoardComment comment : comments) {

                int likeCount = likeRedisService.countLikes(comment.getId(), ContentType.STYLE_BOARD_COMMENT);

                //dto에 comment , like count 매핑
                StyleBoardCommentResponseDTO dto = StyleBoardCommentResponseDTO.from(comment, likeCount);

                dto.setReplies(comment.getChildren().stream()
                        .map(reply -> {
                            int replyLikeCount = likeRedisService.countLikes(reply.getId(), ContentType.STYLE_BOARD_COMMENT);
                            return StyleBoardReplyResponseDTO.from(reply, replyLikeCount);
                        })
                        .collect(Collectors.toList()));

                response.add(dto);
            }

            return response;

        } catch (Exception e) {
            throw new CommentNotFoundException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public StyleBoardComment loadStyleBoardById(Long styleBoardId) {
        return styleBoardCommentRepository.findById(styleBoardId)
                .orElseThrow(() -> new CommentNotFoundException(HttpStatus.BAD_REQUEST.value(), "댓글이 존재하지 않습니다. {id : " + styleBoardId + "}"));
    }
}
