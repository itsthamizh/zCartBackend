package com.example.zCartBackend.controller;

import com.example.zCartBackend.model.Product;
import com.example.zCartBackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add-product")
    public Product addProduct(@RequestBody Product product){
            return productService.addProduct(product);
    }

    @PutMapping("/update-product/{id}")
    public Product updateProduct(@PathVariable String product_id, @RequestBody Product product){
        return productService.updateProduct(product_id, product);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productID){
        String result = productService.deleteProduct(productID);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list-all-products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String productID) {
        Optional<Product> product = productService.getProductById(productID);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
