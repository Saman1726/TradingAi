package com.tradingai.controller;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/status")
    public String getStatus() {
        return "TradingAI API is running";
    }

    @PostMapping("/registration")
    public String registerUser(@RequestBody User user) {
        // Save the user to the database
        userService.saveUser(user);
        return "User registered successfully";
    }

}