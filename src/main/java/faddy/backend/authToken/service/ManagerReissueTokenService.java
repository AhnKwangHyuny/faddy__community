package faddy.backend.authToken.service;

import faddy.backend.auth.api.response.AuthTokensResponse;
import faddy.backend.authToken.infrastructure.AuthTokensGenerator;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.UnAuthorizationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ManagerReissueTokenService {

    private final AuthTokensGenerator authTokensGenerator;
    private final RefreshTokenValidator refreshTokenValidator;

    public AuthTokensResponse reissueToken(final String refreshToken) {

        refreshTokenValidator.validateToken(refreshToken);
        refreshTokenValidator.validateLogoutToken(refreshToken);

        String username = Optional.ofNullable(authTokensGenerator.extractUsername(refreshToken))
                .orElseThrow(() -> new UnAuthorizationException(ExceptionCode.INVALID_AUTH_CODE));

        return authTokensGenerator.generate(username);
    }
}
