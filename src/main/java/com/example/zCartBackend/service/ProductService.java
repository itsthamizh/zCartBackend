package com.example.zCartBackend.service;

import com.example.zCartBackend.model.Product;
import com.example.zCartBackend.repository.ProductRepository;
import com.example.zCartBackend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        product.setProduct_id(Util.getUniqueID());
        product.setAddedDate(Util.getCurrentDate());
        product.setUpdatedDate(Util.getCurrentDate());
        return productRepository.save(product);
    }

    //category update pending
    public Product updateProduct(String productId, Product product){
        Product existingProduct = productRepository.findById(productId).orElse(null);

        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDiscount(product.getDiscount());
            existingProduct.setUpdatedDate(Util.getCurrentDate());

            return productRepository.save(existingProduct);
        }
        return null;
    }
    public String deleteProduct(String productId){
        Optional<Product> optionalUser = productRepository.findById(productId);

        if (optionalUser.isPresent()) {
            productRepository.deleteById(productId);
            return "User with ID " + productId + " deleted successfully.";
        } else {
            return "User with ID " + productId + " does not exist.";
        }
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(String productID) {
        return productRepository.findById(productID);
    }
}
