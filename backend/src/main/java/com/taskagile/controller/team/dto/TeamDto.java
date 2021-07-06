package com.taskagile.controller.team.dto;

import com.taskagile.domain.team.Team;
import lombok.*;

public class TeamDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetTeam {
        private Long id;
        private String name;

        public static GetTeam build(Team team) {
            return GetTeam.builder().id(team.getId()).name(team.getName()).build();
        }
    }
}
