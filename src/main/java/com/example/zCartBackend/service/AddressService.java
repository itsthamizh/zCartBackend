package com.example.zCartBackend.service;

import com.example.zCartBackend.model.Address;
import com.example.zCartBackend.model.Category;
import com.example.zCartBackend.repository.AddressRepository;
import com.example.zCartBackend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address addAddress(Address address){
        address.setAddress_id(Util.getUniqueID());
        address.setCreatedDateTime(Util.getCurrentDate());
        address.setUpdatedDateTime(Util.getCurrentDate());
        return addressRepository.save(address);
    }

    public Address updateAddress(String addressId, Address address){
        Address existingAddress = addressRepository.findById(addressId).orElse(null);

        if (existingAddress != null) {
            existingAddress.setDoorNumber(address.getDoorNumber());
            existingAddress.setFlatName(address.getFlatName());
            existingAddress.setStreet(address.getStreet());
            existingAddress.setArea(address.getArea());
            existingAddress.setDistrict(address.getDistrict());
            existingAddress.setState(address.getState());
            existingAddress.setCountry(address.getCountry());
            existingAddress.setPinCode(address.getPinCode());
            existingAddress.setUpdatedDateTime(Util.getCurrentDate());
            return addressRepository.save(existingAddress);
        }
        return null;
    }
    public String deleteAddress(String addressId){
        Optional<Address> optionalAddress = addressRepository.findById(addressId);

        if (optionalAddress.isPresent()) {
            addressRepository.deleteById(addressId);
            return "User with ID " + addressId + " deleted successfully.";
        } else {
            return "User with ID " + addressId + " does not exist.";
        }
    }
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }
    public Optional<Address> getAddressById(String addressID) {
        return addressRepository.findById(addressID);
    }
    public Address generateAddressFromMap(Map<String, Object> addressMap){
        Address address = new Address();
        try {
            address.setAddress_id(Util.getUniqueID());
            address.setDoorNumber((String) addressMap.get("doorNumber"));
            address.setFlatName((String) addressMap.get("flatName"));
            address.setStreet((String) addressMap.get("street"));
            address.setArea((String) addressMap.get("area"));
            address.setDistrict((String) addressMap.get("district"));
            address.setState((String) addressMap.get("state"));
            address.setCountry((String) addressMap.get("country"));
            address.setPinCode((String) addressMap.get("pincode"));
            address.setCreatedDateTime(Util.getCurrentDate());
            address.setUpdatedDateTime(Util.getCurrentDate());
            return address;
        }catch (Exception e){
            throw new RuntimeException("Missing Address Details");
        }
    }
}
