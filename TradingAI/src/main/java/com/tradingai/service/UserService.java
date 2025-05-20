package com.tradingai.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tradingai.model.User;
import com.tradingai.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
       return userRepository.save(user);
    }

    public User findByEmail(String email) {

        // Check if the user already exists in the database
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }

        // If the user doesn't exist, register a new user
        User newUser = new User();
        newUser.setEmail(email);
        return newUser;
    }
}