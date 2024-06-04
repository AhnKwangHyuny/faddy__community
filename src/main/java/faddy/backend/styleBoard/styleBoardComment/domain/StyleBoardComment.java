package faddy.backend.styleBoard.styleBoardComment.domain;

import faddy.backend.global.BaseEntity;
import faddy.backend.styleBoard.domain.StyleBoard;
import faddy.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class StyleBoardComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_board_id", nullable = false)
    private StyleBoard styleBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Lob
    @Column(name = "content", nullable = false , columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private StyleBoardComment parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StyleBoardComment> children = new ArrayList<>();

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "likes", nullable = false)
    private int likes = 0;

    // 댓글 생성자
    public StyleBoardComment(StyleBoard styleBoard, User user, String content) {
        this.styleBoard = styleBoard;
        this.user = user;
        this.content = content;
    }

    // 대댓글 생성 메서드
    public static StyleBoardComment createReply(StyleBoardComment parent, User user, String content) {
        if (parent.getParent() != null) {
            throw new IllegalArgumentException("대댓글에는 대댓글을 달 수 없습니다.");
        }
        StyleBoardComment reply = new StyleBoardComment(parent.getStyleBoard(), user, content);
        reply.parent = parent;
        parent.addChild(reply);
        return reply;
    }

    // 대댓글을 위한 연관관계 설정
    private void addChild(StyleBoardComment child) {
        if (this.parent != null) {
            throw new IllegalArgumentException("대댓글에는 대댓글을 달 수 없습니다.");
        }
        this.children.add(child);
    }

    // 댓글 삭제 메서드
    public void delete() {
        this.isDeleted = true;
        this.content = "삭제된 댓글입니다.";
    }

    // 좋아요 추가 메서드
    public void addLike() {
        this.likes++;
    }

    // 좋아요 취소 메서드
    public void removeLike() {
        if (this.likes > 0) {
            this.likes--;
        }
    }
}
