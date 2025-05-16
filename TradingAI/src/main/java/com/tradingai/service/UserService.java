package com.tradingai.service;

import java.util.Optional;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.tradingai.model.User;
import com.tradingai.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User processOAuth2User(OAuth2User oAuth2User) {
        // Extract user information from OAuth2User
        String username = oAuth2User.getAttribute("login"); // For GitHub, "login" is the username
        String email = oAuth2User.getAttribute("email");

        // Check if the user already exists in the database
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }

        // If the user doesn't exist, register a new user
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        return userRepository.save(newUser);
    }
}