package com.tradingai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.tradingai.model.User;
import com.tradingai.service.UserService;
import com.tradingai.DTO.TokenRequest;

// Add these imports for GoogleIdToken and related classes
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.nimbusds.jwt.JWT;
// Import GoogleVerifierService
import com.tradingai.service.GoogleVerifierService;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;
    private final GoogleVerifierService googleVerifierService;

    public AuthController(UserService userService, GoogleVerifierService googleVerifierService) {
        this.userService = userService;
        this.googleVerifierService = googleVerifierService;
    }

    @PostMapping("/api/public/login")
    public ResponseEntity<User> login(@AuthenticationPrincipal Jwt jwt) {

        if (jwt == null ) {
            logger.error("Token is null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String email = jwt.getClaim("email");
        String name = jwt.getClaim("name");

        User user = new User();
        user.setEmail(email);
        user.setUsername(name);
        userService.saveUser(user);
        logger.debug("Logged in User: {}", name);
        return ResponseEntity.ok(user);

    }
}