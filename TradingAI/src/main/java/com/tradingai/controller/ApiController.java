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

import com.tradingai.model.User;
import com.tradingai.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private final UserService userService;

    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registerUser(@AuthenticationPrincipal Jwt jwt, @RequestBody User user) {
        if (jwt == null) {
            logger.error("Token is null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String email = jwt.getClaim("email");
        String name = jwt.getClaim("name");
        User userObject = userService.findByEmail(email);
        userObject.setEmail(email);
        userObject.setUsername(name);
        userObject.setPassword(user.getPassword());
        userObject.setFirstName(user.getFirstName());
        userObject.setLastName(user.getLastName());
        userObject.setPhone(user.getPhone());
        userObject.setStreet(user.getStreet());
        userObject.setCity(user.getCity());
        userObject.setState(user.getState());
        userObject.setZip(user.getZip());
    
        userService.saveUser(userObject);
        logger.debug("Logged in User: {}", name);
        return ResponseEntity.ok(user);

    }

}