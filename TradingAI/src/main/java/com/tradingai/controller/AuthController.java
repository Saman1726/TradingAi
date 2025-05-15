package com.tradingai.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingai.model.User;
import com.tradingai.service.UserService;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/private/welcome")
    public String welcome(@AuthenticationPrincipal OAuth2User oAuth2User) {
        // Process the OAuth2User and register or retrieve the user
        User user = userService.processOAuth2User(oAuth2User);
        return "Welcome, " + user.getUsername() + "!";
    }
}