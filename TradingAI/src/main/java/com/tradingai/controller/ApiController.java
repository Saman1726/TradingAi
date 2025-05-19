package com.tradingai.controller;

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

    private final UserService userService;
    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registerUser(@RequestBody User user, AuthenticationPrincipal oAuth2User) {
         if(oAuth2User == null) {
            return ResponseEntity.badRequest().body(null);
        }
        // Save the user to the database
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

}