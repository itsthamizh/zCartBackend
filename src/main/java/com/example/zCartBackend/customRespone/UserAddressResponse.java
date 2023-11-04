package com.example.zCartBackend.customRespone;

import com.example.zCartBackend.model.Address;
import com.example.zCartBackend.model.User;

/*
* @Author itsthamizh
*
* This class for Only Return User with Address as Response From adding User Functionality
*
* */
public class UserAddressResponse {
    private User user;
    private Address address;
    public UserAddressResponse(){}
    public UserAddressResponse(User user, Address address) {
        this.user = user;
        this.address = address;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
}
