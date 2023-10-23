package com.example.zCartBackend.service;

import com.example.zCartBackend.dto.JwtAuthenticationResponse;
import com.example.zCartBackend.dto.SignInRequest;
import com.example.zCartBackend.dto.SignUpRequest;
import com.example.zCartBackend.model.Role;
import com.example.zCartBackend.model.User;
import com.example.zCartBackend.repository.UserRepository;
import com.example.zCartBackend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
//        var user = User
//                .builder()
//                .username(request.getUsername())
//                .lastName(request.getLastName())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.ROLE_USER)
//                .build();

        User user = new User(Util.getUniqueID(), request.getUsername(), passwordEncoder.encode(request.getPassword()), request.getMobile_number(), request.getName(), request.getCredit());
        user = userService.save(user);
        var jwt = jwtService.generateToken((UserDetails) user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}