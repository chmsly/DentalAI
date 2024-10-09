package com.dentalai.repository;

import com.dentalai.model.User;

import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}