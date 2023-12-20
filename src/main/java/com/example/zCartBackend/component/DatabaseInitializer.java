package com.example.zCartBackend.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Check if the user table exists
        if (!tableExists("user")) {
            createUserTable();
        }
        // Check if the product table exists
        if (!tableExists("product")) {
            createProductTable();
        }
        // Check if the category table exists
        if (!tableExists("category")) {
            createCategoryTable();
        }
        // Check if the address table exists
        if (!tableExists("address")) {
            createAddressTable();
        }
    }

    private boolean tableExists(String tableName) {
        String query = "SHOW TABLES LIKE ?";
        List<String> result = jdbcTemplate.queryForList(query, String.class, tableName);
        return !result.isEmpty();
    }
    private void createUserTable() {
        String createTableSQL = "CREATE TABLE user (" +
                "    user_id VARCHAR(255) PRIMARY KEY," +
                "    username VARCHAR(255)," +
                "    password VARCHAR(255)," +
                "    name VARCHAR(255)," +
                "    mobile_number VARCHAR(255)," +
                "    credit BIGINT," +
                "    roles VARCHAR(255)," +
                "    created_date_time VARCHAR(255)," +
                "    updated_date_time VARCHAR(255)," +
                "    address_id VARCHAR(255)" +
                ");";

        jdbcTemplate.execute(createTableSQL);

        System.out.println("User - Table created successfully.");
    }

    private void createCategoryTable() {
        String createTableSQL = "CREATE TABLE category (" +
                "    category_id VARCHAR(255) PRIMARY KEY," +
                "    category_name VARCHAR(255)," +
                "    added_date_time VARCHAR(255)," +
                "    updated_date_time VARCHAR(255)" +
                ");";

        jdbcTemplate.execute(createTableSQL);

        System.out.println("Category - Table created successfully.");
    }

    private void createProductTable() {
        String createTableSQL = "CREATE TABLE product (" +
                "    product_id VARCHAR(255) PRIMARY KEY," +
                "    category_id VARCHAR(255)," +
                "    product_name VARCHAR(255)," +
                "    price BIGINT," +
                "    discount INT," +
                "    added_date VARCHAR(255)," +
                "    updated_date VARCHAR(255)" +
                ");";

        jdbcTemplate.execute(createTableSQL);

        System.out.println("Product - Table created successfully.");
    }
    private void createAddressTable() {
        String createTableSQL = "CREATE TABLE address (" +
                "    address_id VARCHAR(255) PRIMARY KEY," +
                "    door_number VARCHAR(255)," +
                "    flat_name VARCHAR(255)," +
                "    street VARCHAR(255)," +
                "    area VARCHAR(255)," +
                "    district VARCHAR(255)," +
                "    state VARCHAR(255)," +
                "    country VARCHAR(255)," +
                "    pin_code VARCHAR(255)," +
                "    created_date_time VARCHAR(255)," +
                "    updated_date_time VARCHAR(255)" +
                ");";

        jdbcTemplate.execute(createTableSQL);

        System.out.println("Address - Table created successfully.");
    }
}