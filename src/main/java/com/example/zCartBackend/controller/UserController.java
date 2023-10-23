package com.example.zCartBackend.controller;

import com.example.zCartBackend.model.User;
import com.example.zCartBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list-all-users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/get-user/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/add-user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
//        user.setId(id); // Set the ID of the user to update
//        return userRepository.save(user);
        return new User();
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        try {
            userRepository.deleteById(id);
            return "Deleted SuccessFully";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
