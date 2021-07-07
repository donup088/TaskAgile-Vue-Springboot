package com.taskagile.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardUserRepository extends JpaRepository<BoardUser, Long> {
}
