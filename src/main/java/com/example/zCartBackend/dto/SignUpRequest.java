package com.example.zCartBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    String username;
    String password;
    String name;
    String mobile_number;
    long credit;

//    public SignUpRequest(){}
//    public SignUpRequest(String username, String password, String name, String mobile_number, long credit) {
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.mobile_number = mobile_number;
//        this.credit = credit;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public long getCredit() {
        return credit;
    }
    public void setCredit(long credit) {
        this.credit = credit;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
