package com.example.OrderManagementSystem.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderManagementSystem.Entity.Product;
import com.example.OrderManagementSystem.Repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;
	public Product createProduct(Product product) {
		return productRepo.save(product);
		}
    public List<Product> getAllProducts() {
    	return productRepo.findAll(); 
    	}
    public Product getProductById(String id) { 
    	Optional<Product> product = productRepo.findById(id);
    	return product.orElse(null); // Return null if not found }
    }
    public Product updateProduct(String id, Product product) {
    	if (productRepo.existsById(id)) { 
    		product.setId(id);
    		return productRepo.save(product);
    		}
    	return null; // Return null if not found
    	} 
    public boolean deleteProduct(String id) {
    	if (productRepo.existsById(id)) { 
    		productRepo.deleteById(id);
    		return true;
    		} 
    	return false; // Return false if not found }
    }
}
