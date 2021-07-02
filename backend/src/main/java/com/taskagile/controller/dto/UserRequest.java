package com.taskagile.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class UserRequest {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
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
}
