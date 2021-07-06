package com.taskagile.domain.board;

import com.taskagile.domain.user.User;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> findAllByUserInBoardUser(User user);
}
