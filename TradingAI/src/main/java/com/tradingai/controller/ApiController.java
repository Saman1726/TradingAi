package com.tradingai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.tradingai.model.User;
import com.tradingai.service.GoogleVerifierService;
import com.tradingai.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private final UserService userService;
    private final GoogleVerifierService googleVerifierService;

    public ApiController(UserService userService, GoogleVerifierService googleVerifierService) {
        this.userService = userService;
        this.googleVerifierService = googleVerifierService;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registerUser(@AuthenticationPrincipal Jwt jwt, @RequestBody User user) {
        if (jwt == null) {
            logger.error("Token is null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String email = jwt.getClaim("email");
        String name = jwt.getClaim("name");

        user.setEmail(email);
        user.setUsername(name);
        userService.saveUser(user);
        logger.debug("Logged in User: {}", name);
        return ResponseEntity.ok(user);

    }

}