package com.taskagile.controller.team;

import com.taskagile.controller.team.dto.TeamDto;
import com.taskagile.controller.team.dto.TeamRequest;
import com.taskagile.domain.team.Team;
import com.taskagile.securiy.userdetails.CurrentUser;
import com.taskagile.securiy.userdetails.CustomUserDetails;
import com.taskagile.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/team")
public class TeamController {
    private final TeamService teamService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDto.GetOne create(@RequestBody @Valid TeamRequest.create request, @CurrentUser CustomUserDetails customUserDetails) {
        Team team = teamService.create(request, customUserDetails.getUser());
        return TeamDto.GetOne.build(team);
    }
}
