package com.example.zCartBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
public class User implements UserDetails {
    @Id
    private String user_id;
    private String username;
    private String password;
    private String name;
    private String mobile_number;
    private long credit;
    private Set<Role> roles;
    private String createdDateTime;
    private String updatedDateTime;

    public User(){}
    public User(String userId, String userName, String password, String name, String mobile, long credit) {
        this.user_id = userId;
        this.username = userName;
        this.password = password;
        this.name = name;
        this.mobile_number = mobile;
        this.credit = credit;
    }

    public User(String user_id, String username, String password, String name, String mobile_number, long credit, Set<Role> roles, String createdDateTime, String updatedDateTime) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.mobile_number = mobile_number;
        this.credit = credit;
        this.roles = roles;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }


    public String getUserId() {
        return user_id;
    }

    public void setUserId(String userId) {
        this.user_id = userId;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile_number;
    }

    public void setMobile(String mobile) {
        this.mobile_number = mobile;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
