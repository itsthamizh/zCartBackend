package com.example.zCartBackend.service;

import com.example.zCartBackend.model.User;
import com.example.zCartBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
//                return userRepository.findByEmail(username)
                return (UserDetails) userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public User save(User newUser) {
//        if (newUser.getUserId() == null) {
//            newUser.setCreatedAt(LocalDateTime.now());
//        }
//
//        newUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(newUser);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public String deleteUserByUserID(String userID) {
        if (userID == null || userID.equals("null")) {
            return "Invalid User ID";
        }

        return userRepository.findById(userID)
                .map(user -> {
                    userRepository.delete(user);
                    return "User Deleted Successfully";
                })
                .orElse("Can not find User or Invalid User");
    }

    public Object getUserByUserID(String userID){
        try{
            return userRepository.findById(userID).orElse(null);
        }catch (Exception e){
            return e.getMessage();
        }
    }
}