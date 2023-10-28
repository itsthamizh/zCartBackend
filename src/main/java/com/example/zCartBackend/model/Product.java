package com.example.zCartBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    private String product_id;
    private String category_id;
    private String productName;
    private long price;
    private int discount;
    private String addedDate;
    private String updatedDate;

    public Product(){}

    public Product(String product_id, String category_id, String productName, long price, int discount, String addedDate, String updatedDate) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.productName = productName;
        this.price = price;
        this.discount = discount;
        this.addedDate = addedDate;
        this.updatedDate = updatedDate;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
