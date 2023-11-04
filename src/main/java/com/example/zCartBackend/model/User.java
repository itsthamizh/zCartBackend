package com.example.zCartBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
    private String roles;
    private String createdDateTime;
    private String updatedDateTime;
    private String address_id;

    public User(){}
    public User(String user_id, String username, String password, String name, String mobile_number, long credit, String roles, String createdDateTime, String updatedDateTime, String address_id) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.mobile_number = mobile_number;
        this.credit = credit;
        this.roles = roles;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
        this.address_id = address_id;
    }


    public User(String userId, String userName, String password, String name, String mobile, long credit) {
        this.user_id = userId;
        this.username = userName;
        this.password = password;
        this.name = name;
        this.mobile_number = mobile;
        this.credit = credit;
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
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
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

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
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
