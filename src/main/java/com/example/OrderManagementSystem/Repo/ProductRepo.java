package com.example.OrderManagementSystem.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.OrderManagementSystem.Entity.Product;

public interface ProductRepo extends MongoRepository<Product, String> {

}
