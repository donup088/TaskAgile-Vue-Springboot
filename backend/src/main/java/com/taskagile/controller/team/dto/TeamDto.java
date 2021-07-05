package com.taskagile.controller.team.dto;

import lombok.*;

public class TeamDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Team{
        private Long id;
        private String name;
    }
}
