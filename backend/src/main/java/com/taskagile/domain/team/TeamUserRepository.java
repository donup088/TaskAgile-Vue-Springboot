package com.taskagile.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamUserRepository extends JpaRepository<TeamUser, Long> {
    Optional<TeamUser> findByUserIdAndTeamId(Long userId, Long teamId);
}
