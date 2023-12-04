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
        // Check if the table exists
        if (!tableExists("product")) {
            createProductTable();
        }
    }

    private boolean tableExists(String tableName) {
        String query = "SHOW TABLES LIKE ?";
        List<String> result = jdbcTemplate.queryForList(query, String.class, tableName);
        return !result.isEmpty();
    }

    private void createTable() {
        // Define your table creation SQL statement
        String createTableSQL = "CREATE TABLE your_table_name ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "name VARCHAR(255) NOT NULL,"
                + "description VARCHAR(255)"
                + ")";

        // Execute the SQL statement to create the table
        jdbcTemplate.execute(createTableSQL);

        System.out.println("Table created successfully.");
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
}