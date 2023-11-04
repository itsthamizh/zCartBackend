package com.example.zCartBackend.controller;

import com.example.zCartBackend.model.Category;
import com.example.zCartBackend.model.Product;
import com.example.zCartBackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add-category")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PutMapping("/update-category/{category_id}")
    public Category updateCategory(@PathVariable String category_id, @RequestBody Category category){
        return categoryService.updateCategory(category_id, category);
    }

    @DeleteMapping("/delete-category/{category_id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String category_id){
        String result = categoryService.deleteCategory(category_id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list-all-category")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> category = categoryService.getAllCategory();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/get-category/{category_id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String category_id) {
        Optional<Category> category = categoryService.getCategoryById(category_id);

        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
