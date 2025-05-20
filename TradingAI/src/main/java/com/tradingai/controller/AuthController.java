package com.tradingai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.tradingai.model.User;
import com.tradingai.service.UserService;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/public/login")
    public ResponseEntity<User> login(@AuthenticationPrincipal Jwt jwt) {

        if (jwt == null) {
            logger.error("Token is null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String email = jwt.getClaim("email");
        String name = jwt.getClaim("name");

        User user = userService.findByEmail(email);
        user.setEmail(email);
        user.setUsername(name);
        userService.saveUser(user);
        logger.debug("Logged in User: {}", name);
        return ResponseEntity.ok(user);

    }
}