package com.example.dentalxray.service;

import com.example.dentalxray.model.User;
import com.example.dentalxray.repository.UserRepository;
import com.example.dentalxray.exception.CustomException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new CustomException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new CustomException("User not found"));
    }

    public User findById(String id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new CustomException("User not found"));
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}