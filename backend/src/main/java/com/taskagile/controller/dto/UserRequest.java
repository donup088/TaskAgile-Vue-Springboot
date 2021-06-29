package com.taskagile.controller.dto;

import lombok.*;

public class UserRequest {
    @Builder
    @Getter
    @NoArgsConstructor
    @ToString
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Register {
        private String name;
        private String email;
        private String password;
    }

    @Getter
    @ToString
    public static class Login {
        private String email;
        private String password;
    }
}
