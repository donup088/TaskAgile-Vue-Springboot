package com.taskagile.domain.user;

import com.taskagile.domain.common.BaseEntity;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private LocalDateTime refreshTokenExpiredAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public static User create(String name, String email, String password, PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.User)
                .refreshToken(UUID.randomUUID().toString())
                .refreshTokenExpiredAt(LocalDateTime.now())
                .build();
    }
}
