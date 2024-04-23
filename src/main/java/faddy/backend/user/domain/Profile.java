package faddy.backend.user.domain;

import faddy.backend.global.BaseEntity;
import faddy.backend.profile.domain.UserLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "motto" , columnDefinition = "varchar(50) default '자신의 패션 좌우명 또는 짧은 자기소개를 써보세요!!'")
    private String motto;

    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private UserLevel userLevel = UserLevel.LEVEL_1;

    public Profile(String motto) {
        this.motto = motto;
    }
}