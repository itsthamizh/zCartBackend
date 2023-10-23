package com.example.zCartBackend.repository;

import com.example.zCartBackend.model.Inventories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventories, String> {
    @Query("SELECT i FROM Inventories i WHERE i.category = :category AND i.brand = :brand AND i.model = :model")
    Optional<Inventories> findByCategoryBrandAndModel(@Param("category") String category, @Param("brand") String brand, @Param("model") String model);

}
