package com.example.zCartBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    private String address_id;
    private String doorNumber;
    private String flatName;
    private String street;
    private String area;
    private String district;
    private String state;
    private String country;
    private String pinCode;

    public Address(){}
    public Address(String doorNumber, String flatName, String street, String area, String district, String state, String country, String pinCode) {
        this.doorNumber = doorNumber;
        this.flatName = flatName;
        this.street = street;
        this.area = area;
        this.district = district;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getFlatName() {
        return flatName;
    }

    public void setFlatName(String flatName) {
        this.flatName = flatName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
