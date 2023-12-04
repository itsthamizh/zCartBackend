package com.example.zCartBackend.service;

import com.example.zCartBackend.config.PasswordConfig;
import com.example.zCartBackend.customRespone.UserAddressResponse;
import com.example.zCartBackend.model.Address;
import com.example.zCartBackend.model.Role;
import com.example.zCartBackend.model.User;
import com.example.zCartBackend.repository.UserRepository;
import com.example.zCartBackend.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressService addressService;

    private PasswordConfig passwordConfig;

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

    public User addUser(HashMap<String, Object> userInput) throws JsonProcessingException {
        HashMap<String, Object> userMap = (HashMap<String, Object>) userInput.get("user");
        if (userMap != null){
            try {
                User user = new User();
                user.setUserId(Util.getUniqueID());
                user.setName((String) userMap.get("name"));
                user.setUserName((String) userMap.get("email"));
                user.setPassword(new PasswordConfig().passwordEncoder().encode((CharSequence) userMap.get("password")));
                user.setMobile((String) userMap.get("mobileNumber"));
                user.setCredit(0);

                ObjectMapper objectMapper = new ObjectMapper();

                user.setRoles(objectMapper.writeValueAsString(userMap.get("roles")));

                user.setCreatedDateTime(Util.getCurrentDate());
                user.setUpdatedDateTime(Util.getCurrentDate());

                User userResponse = userRepository.save(user);
                return userResponse;
            }catch (Exception e){
                throw new RuntimeException("User Not Added, Facing exception while adding this exception "+ e.getMessage());
            }
        }else {
            throw new RuntimeException("Getting User Input from the Params in Empty");
        }
    }

    public UserAddressResponse createUserWithAddress(Map<String, Object> requestBody) {
        Map<String, Object> userMap = (Map<String, Object>) requestBody.get("user");
        Map<String, Object> addressMap = (Map<String, Object>) requestBody.get("address");

        User user = getUserFromMap(userMap);
        Address address = addressService.generateAddressFromMap(addressMap);

        Address addressResponse = addressService.addAddress(address);

        return Optional.ofNullable(addressResponse)
                .map(response -> {
                    user.setAddress_id(response.getAddress_id());
                    User userResponse = userRepository.save(user);
                    UserAddressResponse userAddressResponse = new UserAddressResponse();
                    userAddressResponse.setUser(userResponse);
                    userAddressResponse.setAddress(response);
                    return userAddressResponse;
                })
                .orElseThrow(() -> new RuntimeException("Facing Add User Address adding Exception"));
    }

    public User updateUser(String userId, User user){
        User existingUser = userRepository.findById(userId).orElse(null);

        if (existingUser != null) {
            existingUser.setUserId(userId);
            existingUser.setName(user.getName());
            existingUser.setUserName(user.getUserName());
            existingUser.setPassword(user.getPassword());
            existingUser.setCredit(user.getCredit());
            existingUser.setMobile(user.getMobile());
            existingUser.setRoles(user.getRoles());
            existingUser.setCreatedDateTime(existingUser.getCreatedDateTime());
            existingUser.setUpdatedDateTime(Util.getCurrentDate()); //set the current dateTime
            existingUser.setAddress_id(existingUser.getAddress_id());

            return userRepository.save(existingUser);
        }
        return null;
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
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
    private User getUserFromMap(Map<String, Object> userMap){
        User user = new User();
        try {
            user.setUserId(Util.getUniqueID());
            user.setUserName((String) userMap.get("username"));
            user.setPassword((String) userMap.get("password"));
            user.setName((String) userMap.get("name"));
            user.setMobile((String) userMap.get("mobile_number"));
            user.setCredit(((Integer) userMap.get("credit")).longValue());

            String roles = getRolesIntoJSON((List<Role>) userMap.get("roles"));
            user.setRoles(roles);

            user.setCreatedDateTime(Util.getCurrentDate());
            user.setUpdatedDateTime(Util.getCurrentDate());
            return user;
        }catch (Exception e){
            throw new RuntimeException("Missing User Details");
        }
    }

    public String getRolesIntoJSON(List<Role> roles){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String rolesJson = objectMapper.writeValueAsString(roles);
            return rolesJson;
        }catch (Exception e){
            throw new RuntimeException("Can not convert the Role values into JSON, Verify the Input params");
        }
    }
}