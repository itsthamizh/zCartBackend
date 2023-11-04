package com.example.zCartBackend.config;

import com.example.zCartBackend.model.Role;
import com.example.zCartBackend.model.User;
import com.example.zCartBackend.service.UserService;
import com.example.zCartBackend.repository.UserRepository;
import com.example.zCartBackend.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(SeedDataConfig.class);

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
//            User admin = User
//                    .builder()
//                    .firstName("admin")
//                    .lastName("admin")
//                    .email("admin@admin.com")
//                    .password(passwordEncoder.encode("password"))
//                    .role(Role.ROLE_ADMIN)
//                    .build();
//            User admin = new User(Util.getUniqueID(), "admin", "admin@admin.com", "admin@123", "7897897890", 10000);
//            userService.save(admin);
//            log.debug("created ADMIN user - {}", admin);
        }
    }

}