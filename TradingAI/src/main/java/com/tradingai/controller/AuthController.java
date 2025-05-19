package com.tradingai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradingai.model.User;
import com.tradingai.service.UserService;
@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/login")
    public ResponseEntity<User> welcome(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if(oAuth2User == null) {
            return ResponseEntity.badRequest().body(null);
        }
        logger.info("User authenticated: {}", oAuth2User.getName());
        User user = userService.processOAuth2User(oAuth2User);
        return ResponseEntity.ok(user);
    }
}