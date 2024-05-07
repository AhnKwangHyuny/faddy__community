package faddy.backend.follows.domain;

import faddy.backend.follows.types.FollowStatus;
import faddy.backend.global.BaseEntity;
import faddy.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "folowing_id")
    private User following;


    @Enumerated(EnumType.STRING)
    private FollowStatus status;

    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }

    // 연관관계 메서드
    public void addToUser(User following) {
        this.following = following;
        following.getFollowings().add(this);
    }

    public void addFromUser(User follower) {
        this.follower = follower;
        follower.getFollowers().add(this);
    }

    @Builder
    public Follow(User follower, User following, FollowStatus status) {
        this.follower = follower;
        this.following = following;
        this.status = (status == null) ? FollowStatus.REQUESTED : status;
        addFromUser(follower);
        addToUser(following);
    }
}