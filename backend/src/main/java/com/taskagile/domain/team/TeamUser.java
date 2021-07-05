package com.taskagile.domain.team;

import com.taskagile.domain.common.BaseEntity;
import com.taskagile.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "team_user")
public class TeamUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private void setTeam(Team team) {
        team.getTeamUsers().add(this);
        this.team = team;
    }

    public static TeamUser create(Team team, User user) {
        TeamUser teamUser = TeamUser.builder().user(user).build();
        teamUser.setTeam(team);
        return teamUser;
    }
}
