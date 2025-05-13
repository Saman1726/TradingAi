package com.tradingai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/status")
    public String getStatus() {
        return "TradingAI API is running";
    }

    // Additional API endpoints can be defined here
}