package com.example.zCartBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {
    @Id
    private String category_id;
    private String categoryName;
    private String addedDateTime;
    private String updatedDateTime;
    public Category(){}

    public Category(String category_id, String categoryName, String addedDateTime, String updatedDateTime) {
        this.category_id = category_id;
        this.categoryName = categoryName;
        this.addedDateTime = addedDateTime;
        this.updatedDateTime = updatedDateTime;
    }

    public String getCategoryID() {
        return category_id;
    }

    public void setCategoryID(String category_id) {
        this.category_id = category_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAddedDateTime() {
        return addedDateTime;
    }

    public void setAddedDateTime(String addedDateTime) {
        this.addedDateTime = addedDateTime;
    }

    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}
