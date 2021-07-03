package com.taskagile.config.security;

import com.taskagile.securiy.token.JwtTokenProvider;
import com.taskagile.securiy.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider tokenProvider;

    @Value("${oauth2.success.redirect.url}")
    private String url;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String id = String.valueOf(oauth2User.getAttributes().get("id"));
        Token token = tokenProvider.generateAccessToken(id);

        response.sendRedirect(url + token.getToken());
    }
}
