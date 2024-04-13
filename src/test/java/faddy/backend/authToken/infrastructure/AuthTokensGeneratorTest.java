package faddy.backend.authToken.infrastructure;

import faddy.backend.auth.api.response.AuthTokensResponse;
import faddy.backend.auth.jwt.Service.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application.yml") //test용 properties 파일 설정
@SpringBootTest
class AuthTokensGeneratorTest {

    @Autowired
    AuthTokensGenerator authTokensGenerator;

    private String accessToken;
    private String refreshToken;

    @BeforeEach
    void setUp() {
        String username = "testUser";

        AuthTokensResponse authTokensResponse = authTokensGenerator.generate(username);

        accessToken = authTokensResponse.getAccessToken();
        refreshToken = authTokensResponse.getRefreshToken();
    }


    @Test
    void isValidToken() {
        // given
        String token = accessToken;

        // when
        boolean isValid = authTokensGenerator.isValidToken(token);

        // then
        assertTrue(isValid);
    }

    @Test
    void extractUsername() {
        // given
        String token = accessToken;
        String expectedUsername = "testUser";

        // when
        String username = authTokensGenerator.extractUsername(token);

        // then
        assertEquals(expectedUsername, username);
    }
}
