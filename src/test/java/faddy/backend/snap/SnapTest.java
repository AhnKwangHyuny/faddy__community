package faddy.backend.snap;

import faddy.backend.snap.domain.Snap;
import faddy.backend.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SnapTest {
    @Test
    void 스냅_엔티티_생성() {
        // given
        User user = new User.Builder()
                .withUsername("user1")
                .withPassword("pass1")
                .withNickname("닉네임1")
                .withEmail("user1@email.com")
                .withAuthority("ROLE_USER")
                .build();

        String description = "테스트 사진";

        // when
        Snap snap = new Snap(user, description);

        // then
        assertEquals(user, snap.getUser());
        assertEquals(description, snap.getDescription());
    }
}
