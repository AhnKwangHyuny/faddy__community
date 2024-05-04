package faddy.backend.follows.domain;

import faddy.backend.follows.types.FollowStatus;
import faddy.backend.global.BaseEntity;
import faddy.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "follows")
@NoArgsConstructor(access = PROTECTED)
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user")
    private User toUser;


    @Enumerated(EnumType.STRING)
    private FollowStatus status;

    public Follow(User fromUser, User toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    // 연관관계 메서드
    public void addToUser(User toUser) {
        this.toUser = toUser;
        toUser.getFollowings().add(this);
    }

    public void addFromUser(User fromUser) {
        this.fromUser = fromUser;
        fromUser.getFollowers().add(this);
    }

    @Builder
    public Follow(User fromUser, User toUser, FollowStatus status) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.status = (status == null) ? FollowStatus.REQUESTED : status;
        addFromUser(fromUser);
        addToUser(toUser);
    }
}