package com.example.zCartBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {
    @Id
    private String categoryID;
    private String categoryName;
    private String addedDateTime;
    private String updatedDateTime;

    public Category(String categoryID, String categoryName, String addedDateTime, String updatedDateTime) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.addedDateTime = addedDateTime;
        this.updatedDateTime = updatedDateTime;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
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
