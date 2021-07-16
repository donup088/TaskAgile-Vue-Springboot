package com.taskagile.controller.user.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

public class UserRequest {
    @Getter
    public static class Register {
        @NotEmpty
        private String name;
        @NotEmpty
        private String email;
        @NotEmpty
        private String password;
    }

    @Getter
    public static class Login {
        @NotEmpty
        private String email;

        private String password;
    }

    @Getter
    public static class RefreshToken {
        @NotEmpty
        private Long userId;
    }
}
