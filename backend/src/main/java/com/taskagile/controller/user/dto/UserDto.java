package com.taskagile.controller.user.dto;

import com.taskagile.domain.user.User;
import lombok.*;

public class UserDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetIdAndName {
        private Long id;
        private String name;

        public static GetIdAndName build(User user) {
            return GetIdAndName.builder().id(user.getId()).name(user.getName()).build();
        }
    }
}
