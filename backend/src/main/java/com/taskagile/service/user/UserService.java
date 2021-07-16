package com.taskagile.service.user;

import com.taskagile.controller.user.dto.UserRequest;
import com.taskagile.controller.user.dto.UserResponse;
import com.taskagile.domain.user.RefreshToken;
import com.taskagile.domain.user.RefreshTokenRepository;
import com.taskagile.domain.user.User;
import com.taskagile.domain.user.UserRepository;
import com.taskagile.securiy.token.Token;
import com.taskagile.securiy.token.TokenProvider;
import com.taskagile.service.user.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public void create(UserRequest.Register request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailExistedException();
        }
        userRepository.save(User.create(request.getName(), request.getEmail(), request.getPassword(), passwordEncoder));
    }

    public UserResponse.Login login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordWrongException();
        }
        Token token = tokenProvider.generateRefreshToken();
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserId(user.getId());
        if (optionalRefreshToken.isPresent()) {
            optionalRefreshToken.get().update(token.getToken(), token.getExpiredAt());
        } else {
            RefreshToken refreshToken = RefreshToken.create(token.getToken(), token.getExpiredAt(), user);
            refreshTokenRepository.save(refreshToken);
        }

        return UserResponse.Login.build(user.getId(), user.getName(), tokenProvider.generateAccessToken(user));
    }

    public UserResponse.Login refreshToken(UserRequest.RefreshToken request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(request.getUserId()).orElseThrow(RefreshTokenNotFoundException::new);
        if (!refreshToken.verifyExpiration()) {
            throw new RefreshTokenExpiredException();
        }
        Token token = tokenProvider.generateRefreshToken();
        refreshToken.update(token.getToken(), token.getExpiredAt());

        return UserResponse.Login.build(user.getId(), user.getName(), tokenProvider.generateAccessToken(user));
    }
}
