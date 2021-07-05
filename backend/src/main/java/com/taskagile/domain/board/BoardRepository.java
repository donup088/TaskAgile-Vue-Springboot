package com.taskagile.domain.board;

import com.taskagile.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByCreatedBy(User user);
}
