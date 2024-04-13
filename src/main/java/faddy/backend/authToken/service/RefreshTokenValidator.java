package faddy.backend.authToken.service;

import faddy.backend.authToken.infrastructure.AuthTokensGenerator;
import faddy.backend.authToken.repository.BlackListTokenRepository;
import faddy.backend.global.exception.AuthorizationException;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.UnAuthorizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RefreshTokenValidator {

    private final AuthTokensGenerator authTokensGenerator;
    private final BlackListTokenRepository blackListTokenRepository;

    public void validateToken(String refreshToken) {
        if (!authTokensGenerator.isValidToken(refreshToken)) {
            throw new AuthorizationException(ExceptionCode.INVALID_REFRESH_TOKEN);
        }
    }

    public void validateTokenOwnerUsername(String refreshToken, String username) {
        final String ownerUsername = authTokensGenerator.extractUsername(refreshToken);
        if (!ownerUsername.equals(username)) {
            throw new AuthorizationException(ExceptionCode.TOKEN_OWNER_MISMATCH);
        }
    }

    public void validateLogoutToken(String refreshToken) {
        if (blackListTokenRepository.existsByInvalidRefreshToken(refreshToken)) {
            throw new UnAuthorizationException(ExceptionCode.ALREADY_LOGOUT);
        }
    }
}
