package com.taskagile.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsUserByKakaoId(Long kakaoId);

    Optional<User> findByName(String name);

    Optional<User> findByKakaoId(Long kakaoId);
}
