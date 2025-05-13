package com.tradingai.service;

import com.tradingai.model.User;
import com.tradingai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Logic for user registration
        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(String username, String password) {
        // Logic for user authentication
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public Optional<User> getUserById(Long id) {
        // Logic to retrieve user by ID
        return userRepository.findById(id);
    }
}