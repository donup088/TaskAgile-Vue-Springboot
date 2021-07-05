package com.taskagile.domain.team;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.taskagile.domain.user.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.taskagile.domain.team.QTeam.team;
import static com.taskagile.domain.team.QTeamUser.teamUser;

@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Team> findAllByUser(User user) {
        return queryFactory.selectFrom(team)
                .join(team.teamUsers, teamUser).on(teamUser.user.id.eq(user.getId()))
                .fetch();
    }
}
