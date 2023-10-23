package com.example.zCartBackend.service;

import com.example.zCartBackend.model.Inventories;
import com.example.zCartBackend.model.User;
import com.example.zCartBackend.repository.InventoryRepository;
import com.example.zCartBackend.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class InitializationService {
    private static final Logger logger = LogManager.getLogger(InitializationService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    InventoryRepository inventoryRepository;

    public boolean loadUserDataFromDirectory(String directoryPath) {
        try {
            File file = new File(directoryPath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            boolean isFirstLine = true; // Add this flag for skip first line(headers)
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the first line
                }
                addInitialUserDetailsToDB(line);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean loadInventoryDataFromDirectory(String directoryPath){
        try {
            File file = new File(directoryPath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            boolean isFirstLine = true; // Add this flag for skip first line(headers)
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the first line
                }
                addInitialInventoryDetailsToDB(line);
            }
        } catch (IOException e) {
            return false;
        }
        return true;

    }

    private boolean addInitialUserDetailsToDB(String userDetails){
        try {
            String[] res = userDetails.split("[,]", 0);
            System.out.println("this is user details => " + userDetails);
            UUID randomUUID = UUID.randomUUID();
            String randomUserID = randomUUID.toString();
            User user = new User(randomUserID, res[0], res[1], res[2], res[3], Long.parseLong(res[4]));
            boolean exists = isUserDataExists(user.getUserName(), user.getPassword());
            if (exists) {
                System.out.println("User Data exists in the database");
                return true;
            } else {
                System.out.println("User Data does not exist in the database");
                userRepository.save(user);
            }
            logger.info("User Added Successfully, user detail userId : "+randomUserID+", userName : "+res[0]);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean addInitialInventoryDetailsToDB(String inventoryDetails){
        try {
            String[] res = inventoryDetails.split("[,]", 0);
            UUID randomUUID = UUID.randomUUID();
            String randomInventoryID = randomUUID.toString();
            Inventories inventories = new Inventories(randomInventoryID, res[0], res[1], res[2], Long.parseLong(res[3]), Long.parseLong(res[4]), Integer.parseInt(res[5]));
            boolean exists = isInventoryDataExists(inventories.getCategory(), inventories.getBrand(), inventories.getModel());
            if (exists) {
                System.out.println("Inventory Data exists in the database");
                return true;
            } else {
                inventoryRepository.save(inventories);
                logger.info("Inventory Added Successfully, user detail userId : "+randomInventoryID+", userName : "+res[0]);
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }

    public boolean isUserDataExists(String userName, String password) {
        Optional<User> entity = userRepository.findByUserNameAndPassword(userName, password);
        return entity.isPresent();
    }

    public boolean isInventoryDataExists(String category, String brand, String model) {
        Optional<Inventories> entity = inventoryRepository.findByCategoryBrandAndModel(category, brand, model);
        return entity.isPresent();
    }

}
