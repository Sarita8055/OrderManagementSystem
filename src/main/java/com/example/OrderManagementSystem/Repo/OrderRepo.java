package com.example.OrderManagementSystem.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.OrderManagementSystem.Entity.Order;

public interface OrderRepo extends MongoRepository<Order, String> {

}
