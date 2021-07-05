package com.taskagile.controller.user.dto;

import lombok.*;

public class UserDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class User {
        private String name;
    }
}
