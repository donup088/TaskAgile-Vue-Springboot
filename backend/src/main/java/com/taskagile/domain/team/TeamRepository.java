package com.taskagile.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> ,TeamRepositoryCustom{
}
