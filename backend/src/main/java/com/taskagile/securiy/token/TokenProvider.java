package com.taskagile.securiy.token;

import com.taskagile.domain.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenProvider {
    private final JwtProps jwtProps;

    public Token generateAccessToken(User user) {
        return generateAccessToken(user, jwtProps.getAccessTokenProps());
    }

    public Token generateRefreshToken() {
        return generateRefreshToken(jwtProps.getRefreshTokenProps());
    }

    public Long getUserIdFromAccessToken(String token) {
        return Long.valueOf(getClaims(token, jwtProps.getAccessTokenProps()).getBody().getSubject());
    }

    private Token generateAccessToken(User user, JwtProps.TokenProps tokenProps) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenProps.getSecret()));

        Date exp = new Date((new Date()).getTime() + tokenProps.getExpirationTimeMilliSec());
        String token = Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setAudience(user.getEmail())
                .setExpiration(exp)
                .signWith(key)
                .compact();

        return Token.create(token, exp);
    }

    private Token generateRefreshToken(JwtProps.TokenProps tokenProps) {
        String token = UUID.randomUUID().toString();
        Date exp = new Date((new Date()).getTime() + tokenProps.getExpirationTimeMilliSec());

        return Token.create(token, exp);
    }

    private Jws<Claims> getClaims(String token, JwtProps.TokenProps tokenProps) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenProps.getSecret()));
        Jws<Claims> claims = null;

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }

        return claims;
    }
}
