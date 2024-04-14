package faddy.backend.auth.jwt.Service;

import faddy.backend.auth.jwt.infrastruture.CustomDeserializer;
import faddy.backend.auth.jwt.infrastruture.CustomSerializer;
import faddy.backend.auth.repository.TokenBlackListRepository;
import faddy.backend.authToken.domain.RefreshToken;
import faddy.backend.authToken.repository.RefreshTokenRepository;
import faddy.backend.global.exception.ExceptionCode;
import faddy.backend.global.exception.JwtValidationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    @Autowired
    private TokenBlackListRepository tokenBlackListRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private CustomSerializer serializer;

    @Autowired
    private CustomDeserializer deserializer;

    private final SecretKey key;


    private final long ACCESS_TOKEN_EXPIRE_TIME;
    private final long REFRESH_TOKEN_EXPIRE_TIME;


    public JwtUtil(@Value("${spring.jwt.secret}") String sign ,
                   @Value("${spring.jwt.token.access.expire-time}") long accessExpiredAt,
                   @Value("${spring.jwt.token.refresh.expire-time}") long refreshExpiredAt) {


        byte[] keyBytes = Decoders.BASE64.decode(sign);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        ACCESS_TOKEN_EXPIRE_TIME = accessExpiredAt;
        REFRESH_TOKEN_EXPIRE_TIME = refreshExpiredAt;
    }

    @Transactional
    public String generate(String subject, Date expiredAt) {

        try {
            return Jwts.builder()
                    .setSubject(subject)
                    .setExpiration(expiredAt)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();

        } catch (JwtException e) {
            throw new JwtValidationException(ExceptionCode.ERROR_TOKEN_CREATE);
        }
    }


    /**
     * 사용자가 보낸 요청 헤더의 'Authorization' 필드에서 토큰을 추출하는 메소드.
     * @param request
     * @return token(String)
     */
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }


    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build().parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            // check sign validation

            String username = claims.getBody().getSubject();
            if (username == null || username.isEmpty()) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtValidationException(ExceptionCode.INVALID_JWT_TOKEN);
        }
    }


    private Date getExpireDate(Long expireTime) {
        Date now = new Date();
        return new Date(System.currentTimeMillis() +  expireTime);
    }

    public String getUsername(String accessToken) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();

            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            // JWT가 만료되었을 경우, 만료된 username 추출 .
            return null;
        } catch (JwtException e) {
            // 다른 모든 JWT 관련 예외 처리
            return null;
        }
    }


    public Boolean isExpired(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .deserializeJsonWith(deserializer)
                    .build()
                    .parseClaimsJws(token);

            return claimsJws.getBody().getExpiration().before(new Date());

        } catch (JwtException e) {
            return true;
        }
    }



    /**
     * @return refresh token redis 저장
     * @param :  username , String refresh_token
     * */
    @Transactional
    public void storeRefreshToken(String username, String refresh_token) {

        RefreshToken refreshToken = new RefreshToken(username , refresh_token);

        refreshTokenRepository.save(refreshToken);

    }

    public String findRefreshToken(String username) {
        return refreshTokenRepository.findById(username)
                .map(RefreshToken::getRefreshToken)
                .orElse(null);
    }
}
