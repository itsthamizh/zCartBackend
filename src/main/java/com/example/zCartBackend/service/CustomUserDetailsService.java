package com.example.zCartBackend.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Here you can load user information from your database or any other source.
        // For simplicity, we'll hardcode a user.
        if (username.equals("user")) {
            return User.withUsername("user").password("password").roles("USER").build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
