package com.example.zCartBackend.component;

import com.example.zCartBackend.service.InitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderRunner implements ApplicationRunner {
    @Autowired
    private InitializationService customerInitializationService;

    @Value("${data.directory.userPath}") // You can define the directory path in your application.properties or application.yml
    private String userDirectoryPath;

    @Value("${data.directory.inventoryPath}") // You can define the directory path in your application.properties or application.yml
    private String inventoryDirectoryPath;

    @Override
    public void run(ApplicationArguments args) {
        customerInitializationService.loadUserDataFromDirectory(userDirectoryPath);
        customerInitializationService.loadInventoryDataFromDirectory(inventoryDirectoryPath);
    }
}
