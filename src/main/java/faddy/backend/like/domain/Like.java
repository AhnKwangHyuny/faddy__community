package faddy.backend.like.domain;

import faddy.backend.global.BaseEntity;
import faddy.backend.snap.domain.Snap;
import faddy.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(callSuper = true)
@Where(clause = "deleted_at is null")
@Table(name = "likes")
public class Like extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snap_id")
    private Snap snap;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "style_talk_id")
//    private


    public Like(User user, Snap snap) {
        this.user = user;
        this.snap = snap;
    }


    /**
     *  <p>
     *      좋아요 복귀 메소드
     *  </p>
     * */
    public void recoverLike(Like likes) {

        if(this.id == likes.getId()) {
            this.deletedAt = null ;

        }
    }

}