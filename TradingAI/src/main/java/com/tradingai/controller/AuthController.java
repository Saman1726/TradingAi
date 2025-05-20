package com.tradingai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.tradingai.model.User;
import com.tradingai.service.UserService;
import com.tradingai.DTO.TokenRequest;

// Add these imports for GoogleIdToken and related classes
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

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
    public ResponseEntity<User> login(@RequestBody TokenRequest tokenRequest) {
        GoogleIdToken idToken;
        try {
            idToken = googleVerifierService.verify(tokenRequest.getToken());
        } catch (Exception e) {
            logger.error("Error verifying token: {}" , e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            User user = new User();
            user.setEmail(email);
            user.setUsername(name);
            userService.saveUser(user);
            logger.debug("Logged in User: {}" , name);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}