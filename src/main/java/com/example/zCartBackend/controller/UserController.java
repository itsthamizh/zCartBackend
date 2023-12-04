package com.example.zCartBackend.controller;

import com.example.zCartBackend.customRespone.UserAddressResponse;
import com.example.zCartBackend.model.User;
import com.example.zCartBackend.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list-all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get-user/{id}")
    public User getUserByUserId(@PathVariable String id) {
        return (User) userService.getUserByUserID(id);
    }

    @PostMapping("/add-user")
    public ResponseEntity<Object> createUser(@RequestBody HashMap<String, Object> user) {
        User userResponse = null;
        try {
            userResponse = userService.addUser(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(userResponse);
    }
    @PostMapping("/add-user-with-address")
    public ResponseEntity<UserAddressResponse> createUserWithAddress(@RequestBody Map<String, Object> requestBody) {
        UserAddressResponse userAddressResponse = userService.createUserWithAddress(requestBody);
        return ResponseEntity.ok(userAddressResponse);
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails) {
        User user = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUserByUserID(@PathVariable String id) {
        return userService.deleteUserByUserID(id);
    }
}
