package com.taskagile.service.user;

import com.taskagile.controller.user.dto.UserRequest;
import com.taskagile.controller.user.dto.UserResponse;
import com.taskagile.domain.user.User;
import com.taskagile.domain.user.UserRepository;
import com.taskagile.securiy.token.JwtTokenProvider;
import com.taskagile.securiy.token.Token;
import com.taskagile.service.user.exception.EmailExistedException;
import com.taskagile.service.user.exception.PasswordWrongException;
import com.taskagile.service.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

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

        return UserResponse.Login.from(tokenProvider.generateAccessToken(user), new Token(user.getRefreshToken(), user.getRefreshTokenExpiredAt()));
    }
}
