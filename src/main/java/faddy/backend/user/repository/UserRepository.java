package faddy.backend.user.repository;

import com.querydsl.core.annotations.QueryEmbeddable;
import faddy.backend.user.domain.Profile;
import faddy.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    /**
     * 주어진 사용자 이름으로 사용자를 찾는 메소드.
     * @param username 찾고자 하는 사용자의 이름.
     * @return 이름이 일치하는 사용자를 Optional로 감싼 객체. 사용자가 없으면 Optional.empty()를 반환.
     */
    Optional<User> findByUsername(String username);

    @Query("SELECT u.username FROM User u WHERE u.username = ?1")
    Optional<String> findUsernameByUsername(String username);

    @Query("SELECT u.nickname FROM User u WHERE u.nickname = ?1")
    Optional<String> findNicknameByNickname(String nickname);

    @Query("SELECT u.email FROM User u WHERE u.email = ?1")
    Optional<String> findEmailByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(Long userId);
    @Modifying
    @Query(
            """
            UPDATE User user SET user.status = 'DELETED' WHERE user.id = :userId
            """
    )
    void deleteByUserId(@Param("userId") final Long userId);

    @Query("SELECT u.id FROM User u WHERE u.username = :username")
    Long findUserIdByUsername(@Param("username") String username);

    //여러 유저 아이디 셋으로 해당 유저 entity list 반환
    @Query("SELECT u FROM User u WHERE u.id IN (:userIds)")
    List<User> findByUserIds(@Param("userIds") List<Long> userIds);

    // find nickname by id
    @Query("SELECT u.nickname FROM User u WHERE u.id = :userId")
    Optional<String> findNicknameByUserId(@Param("userId") Long userId);

    //find username by id
    @Query("SELECT u.username FROM User u WHERE u.id = :userId")
    Optional<String> findUsernameByUserId(@Param("userId") Long userId);

    // userId 리스트로 nickname 리스트 조회
    @Query("SELECT u.nickname FROM User u WHERE u.id IN (:userIds)")
    List<String> findNicknamesByUserIds(@Param("userIds") List<Long> userIds);

    @Query("SELECT u.profile FROM User u WHERE u.id = :userId")
    Optional<Profile> findProfileById(@Param("userId") Long userId);

    @Query("SELECT u FROM User u JOIN FETCH u.profile WHERE u.username = :username")
    Optional<User> findUserByUsernameWithProfile(@Param("username") String username);

    // 채팅방 모든 유저 정보와 프로필 정보 조회
    @Query("SELECT u FROM User u JOIN FETCH u.profile WHERE u.id IN (:userIds)")
    List<User> findUsersWithProfileByUserIds(@Param("userIds") List<Long> userIds);

}

