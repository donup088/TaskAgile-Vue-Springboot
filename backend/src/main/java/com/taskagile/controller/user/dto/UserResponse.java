package com.taskagile.controller.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskagile.securiy.token.Token;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class UserResponse {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Login {
        private final String name;
        private final String accessToken;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private final LocalDateTime accessTokenExpiredAt;
        private final String refreshToken;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private final LocalDateTime refreshTokenExpiredAt;

        public static Login from(String name, Token accessToken, Token refreshToken) {
            return new Login(
                    name,
                    accessToken.getToken(),
                    accessToken.getExpiredAt(),
                    refreshToken.getToken(),
                    refreshToken.getExpiredAt()
            );
        }
    }
}
