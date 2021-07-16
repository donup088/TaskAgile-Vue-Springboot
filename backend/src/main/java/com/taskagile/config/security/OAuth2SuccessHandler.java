package com.taskagile.config.security;

import com.taskagile.domain.user.RefreshToken;
import com.taskagile.domain.user.RefreshTokenRepository;
import com.taskagile.domain.user.User;
import com.taskagile.domain.user.UserRepository;
import com.taskagile.securiy.token.Token;
import com.taskagile.securiy.token.TokenProvider;
import com.taskagile.service.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    @Value("${oauth2.success.redirect.url}")
    private String url;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String kakaoId = String.valueOf(oauth2User.getAttributes().get("id"));
        User user = userRepository.findByKakaoId(Long.valueOf(kakaoId)).orElseThrow(UserNotFoundException::new);

        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserId(user.getId());
        Token token = tokenProvider.generateAccessToken(kakaoId);
        if (optionalRefreshToken.isPresent()) {
            optionalRefreshToken.get().update(token.getToken(), token.getExpiredAt());
        } else {
            RefreshToken refreshToken = RefreshToken.create(token.getToken(), token.getExpiredAt(), user);
            refreshTokenRepository.save(refreshToken);
        }
        response.sendRedirect(url + token.getToken());
    }
}
