package com.taskagile.service.team;

import com.taskagile.controller.team.dto.TeamRequest;
import com.taskagile.domain.team.Team;
import com.taskagile.domain.team.TeamRepository;
import com.taskagile.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public Team create(TeamRequest.create request, User user) {
        Team team = Team.create(request, user);
        return teamRepository.save(team);
    }
}
