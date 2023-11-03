package com.example.zCartBackend.controller;

import com.example.zCartBackend.model.Address;
import com.example.zCartBackend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/add-address")
    public Address addAddress(@RequestBody Address address){
        return addressService.addAddress(address);
    }

    @PutMapping("/update-address/{id}")
    public Address updateAddress(@PathVariable String address_id, @RequestBody Address address){
        return addressService.updateAddress(address_id, address);
    }

    @DeleteMapping("/delete-address/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String addressID){
        String result = addressService.deleteAddress(addressID);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list-all-address")
    public ResponseEntity<List<Address>> getAllAddress() {
        List<Address> address = addressService.getAllAddress();
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/get-address/{id}")
    public ResponseEntity<Address> getProductById(@PathVariable String addressID) {
        Optional<Address> address = addressService.getAddressById(addressID);

        if (address.isPresent()) {
            return ResponseEntity.ok(address.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
