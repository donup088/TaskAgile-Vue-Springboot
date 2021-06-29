package com.taskagile.controller.dto;

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
        private String accessToken;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime accessTokenExpiredAt;
        private String refreshToken;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime refreshTokenExpiredAt;

        public static Login from(Token accessToken, Token refreshToken) {
            return new Login(
                    accessToken.getToken(),
                    accessToken.getExpiredAt(),
                    refreshToken.getToken(),
                    refreshToken.getExpiredAt()
            );
        }
    }
}
