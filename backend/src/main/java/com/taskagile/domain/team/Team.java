package com.taskagile.domain.team;

import com.taskagile.controller.team.dto.TeamRequest;
import com.taskagile.domain.common.BaseEntity;
import com.taskagile.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "team")
public class Team extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @Builder.Default
    private final List<TeamUser> teamUsers = new ArrayList<>();

    public static Team create(TeamRequest.create request, User user) {
        Team team = Team.builder().name(request.getName()).build();
        TeamUser.create(team, user);
        return team;
    }
}
