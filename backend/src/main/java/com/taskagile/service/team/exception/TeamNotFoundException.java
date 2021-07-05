package com.taskagile.service.team.exception;

import com.taskagile.exception.EntityNotFoundException;

public class TeamNotFoundException extends EntityNotFoundException {
    public TeamNotFoundException() {
        super("해당 팀이 존재하지 않습니다.");
    }
}
