package com.example.zCartBackend.controller;

import com.example.zCartBackend.model.User;
import com.example.zCartBackend.repository.UserRepository;
import com.example.zCartBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/list-all-users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/get-user/{id}")
    public User getUserByUserId(@PathVariable String userID) {
        return (User) userService.getUserByUserID(userID);
    }

    @PostMapping("/add-user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/update-user/{id}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        //tmporary return data
        return new User();
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUserByUserID(@PathVariable String id) {
        return userService.deleteUserByUserID(id);
    }
}
