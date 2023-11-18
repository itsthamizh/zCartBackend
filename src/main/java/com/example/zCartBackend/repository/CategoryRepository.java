package com.example.zCartBackend.repository;

import com.example.zCartBackend.model.Category;
import com.example.zCartBackend.model.Inventories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query("SELECT i FROM Category i WHERE i.categoryName = :categoryName")
    Optional<Category> findByCategory(@Param("categoryName") String categoryName);

}
