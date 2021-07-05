package com.taskagile.domain.team;

import com.taskagile.domain.user.User;

import java.util.List;

public interface TeamRepositoryCustom {
    List<Team> findAllByUser(User user);
}
