package com.example.zCartBackend.config;

import com.example.zCartBackend.filters.JwtAuthenticationFilter;
import com.example.zCartBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private  JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private  UserService userService;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .disable()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v1/signup", "/api/v1/signin").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/test/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/dashboard").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/add-user").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/update-user/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/list-all-users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/get-user/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/delete-user/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/add-category").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/list-all-category").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/list-all-category/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/add-product").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/list-all-products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/get-product/").permitAll()

                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
