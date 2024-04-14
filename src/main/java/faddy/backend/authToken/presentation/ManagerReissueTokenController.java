package faddy.backend.authToken.presentation;

import faddy.backend.auth.api.response.AuthTokensResponse;
import faddy.backend.authToken.service.ManagerReissueTokenService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.boot.web.server.Cookie.SameSite.NONE;
import static org.springframework.http.HttpHeaders.SET_COOKIE;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auths")
public class ManagerReissueTokenController {

    private final ManagerReissueTokenService managerReissueTokenService;

    @GetMapping("/reissue-token")
    public ResponseEntity<AuthTokensResponse> reissueToken(
            @CookieValue(name = "REFRESH_TOKEN") String refreshToken,
            HttpServletResponse response
    ) {
        AuthTokensResponse tokenResponse = managerReissueTokenService.reissueToken(refreshToken);
        ResponseCookie cookie = ResponseCookie.from("REFRESH_TOKEN", tokenResponse.getRefreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/api/auth-tokens-reissue-token")
                .sameSite(NONE.attributeValue())
                .build();
        response.addHeader(SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(tokenResponse);
    }
}
