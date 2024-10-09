package com.dentalai.security;

import com.dentalai.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Autowired
    private JwtUtils jwtUtils;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        return jwtUtils.generateToken(username);
    }
}
