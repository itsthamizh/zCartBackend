package com.example.zCartBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventories")
public class Inventories {
    @Id
    private String category_id;
    private String category;
    private String brand;
    private String model;
    private long price;
    private long stock;
    private int discount;

    public Inventories(){};
    public Inventories(String category_id, String category, String brand, String model, long price, long stock, int discount) {
        this.category_id = category_id;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
