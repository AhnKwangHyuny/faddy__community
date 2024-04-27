package faddy.backend.follows.domain;

import faddy.backend.follows.types.FollowStatus;
import faddy.backend.global.BaseEntity;
import faddy.backend.user.domain.User;
import jakarta.persistence.*;
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

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "followed_id", nullable = false)
    private User followed;

    @Enumerated(EnumType.STRING)
    private FollowStatus status;

    public Follow(User follower, User followed) {
        this.follower = follower;
        this.followed = followed;
        this.status = FollowStatus.REQUESTED;
    }

    // 연관관계 메서드
    public void updateFollower(User follower) {
        this.follower = follower;
        follower.getFollowings().add(this);
    }

    public void updateFollowed(User followed) {
        this.followed = followed;
        followed.getFollowers().add(this);
    }
}