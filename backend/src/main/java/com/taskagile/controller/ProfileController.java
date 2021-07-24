package com.taskagile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class ProfileController {
    private final Environment environment;

    @GetMapping("/profile")
    public String profile(){
        List<String> profiles= Arrays.asList(environment.getActiveProfiles());
        for (String profile : profiles) {
            System.out.println("profile = " + profile);
        }
        return "success-and-change";
    }
}
