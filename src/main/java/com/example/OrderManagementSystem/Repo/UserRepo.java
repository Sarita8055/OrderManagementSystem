package com.example.OrderManagementSystem.Repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.OrderManagementSystem.Entity.User;

public interface UserRepo extends MongoRepository<User, String> {
	Optional<User> findByEmail(String email);

}
