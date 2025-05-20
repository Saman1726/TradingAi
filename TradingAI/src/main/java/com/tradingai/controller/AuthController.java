package com.tradingai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.tradingai.model.User;
import com.tradingai.service.UserService;
import com.tradingai.DTO.TokenRequest;

// Add these imports for GoogleIdToken and related classes
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;


import java.util.Collections;

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

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody TokenRequest tokenRequest) {
        GoogleIdToken idToken = verifyToken(tokenRequest.getToken());

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            String email = payload.getEmail();
            String name = (String) payload.get("name");

            User user = new User();
            user.setEmail(email);
            user.setUsername(name);
            userService.saveUser(user);
            // Authenticate or register the user in your system
            // Optionally generate your own JWT here for session

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    private GoogleIdToken verifyToken(String token) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                    .Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList("YOUR_GOOGLE_CLIENT_ID"))
                    .build();

            return verifier.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}