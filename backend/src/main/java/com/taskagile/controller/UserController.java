package com.taskagile.controller;

import com.taskagile.controller.dto.UserRequest;
import com.taskagile.controller.dto.UserResponse;
import com.taskagile.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user")
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRequest.Register request) {
        userService.create(request);
    }

    @PostMapping("/login")
    public UserResponse.Login login(@RequestBody @Valid UserRequest.Login request) {
        return userService.login(request.getEmail(), request.getPassword());
    }
}
