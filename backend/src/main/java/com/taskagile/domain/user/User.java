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

    private Long kakaoId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private SignupProvider provider;

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
                .provider(SignupProvider.TASKAGILE)
                .refreshToken(UUID.randomUUID().toString())
                .refreshTokenExpiredAt(LocalDateTime.now())
                .build();
    }

    public static User createKakao(Long id, String name) {
        return User.builder()
                .kakaoId(id)
                .name(name)
                .role(Role.User)
                .provider(SignupProvider.KAKAO)
                .refreshToken(UUID.randomUUID().toString())
                .refreshTokenExpiredAt(LocalDateTime.now())
                .build();
    }
}
