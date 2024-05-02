package faddy.backend.user.domain;

import faddy.backend.global.BaseEntity;
import faddy.backend.profile.domain.UserLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static faddy.backend.global.Constants.DEFAULT_PROFILE_IMAGE_URL;
import static faddy.backend.global.Constants.DEFAULT_PROFILE_MOTTO;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "Profiles")
@NoArgsConstructor(access = PROTECTED)
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", nullable = false)
    private Long id;

    @Column(name = "motto")
    private String motto = DEFAULT_PROFILE_MOTTO;

    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private UserLevel userLevel = UserLevel.LEVEL_1;

    @Column(name = "profile_image_url")
    private String profileImageUrl = DEFAULT_PROFILE_IMAGE_URL;

    public Profile(String motto) {
        this.motto = motto;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", motto='" + motto + '\'' +
                ", userLevel=" + userLevel +
                '}';
    }
}