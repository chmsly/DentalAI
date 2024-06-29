package com.example.dentalxray.service;

import com.example.dentalxray.model.User;
import com.example.dentalxray.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}