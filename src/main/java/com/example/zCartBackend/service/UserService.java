package com.example.zCartBackend.service;

import com.example.zCartBackend.model.Role;
import com.example.zCartBackend.model.User;
import com.example.zCartBackend.repository.UserRepository;
import com.example.zCartBackend.util.ConstantVariables;
import com.example.zCartBackend.util.Util;
import org.aspectj.apache.bcel.classfile.ConstantValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Set;

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
        Set<Role> getRoles = user.getRoles();
        if (getRoles.contains(ConstantVariables.USER)){
            user.setRoles(Collections.singleton(Role.ROLE_USER));
        }

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