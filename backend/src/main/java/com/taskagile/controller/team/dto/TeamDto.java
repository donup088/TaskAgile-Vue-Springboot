package com.taskagile.controller.team.dto;

import com.taskagile.domain.team.Team;
import lombok.*;

public class TeamDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetOne {
        private Long id;
        private String name;

        public static GetOne build(Team team) {
            if (team == null) {
                return null;
            }
            return GetOne.builder().id(team.getId()).name(team.getName()).build();
        }
    }
}
