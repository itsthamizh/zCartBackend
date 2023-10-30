package com.example.zCartBackend.service;

import com.example.zCartBackend.model.Category;
import com.example.zCartBackend.model.Product;
import com.example.zCartBackend.repository.CategoryRepository;
import com.example.zCartBackend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category){
        category.setCategoryID(Util.getUniqueID());
        category.setAddedDateTime(Util.getCurrentDate());
        category.setUpdatedDateTime(Util.getCurrentDate());
        return categoryRepository.save(category);
    }

    public Category updateCategory(String categoryId, Category category){
        Category existingCategory = categoryRepository.findById(categoryId).orElse(null);

        if (existingCategory != null) {
            existingCategory.setCategoryName(category.getCategoryName());
            existingCategory.setUpdatedDateTime(Util.getCurrentDate());

            return categoryRepository.save(existingCategory);
        }
        return null;
    }
    public String deleteCategory(String categoryId){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return "User with ID " + categoryId + " deleted successfully.";
        } else {
            return "User with ID " + categoryId + " does not exist.";
        }
    }
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
    public Optional<Category> getCategoryById(String categoryID) {
        return categoryRepository.findById(categoryID);
    }
}
