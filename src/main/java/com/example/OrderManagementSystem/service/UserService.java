package com.example.OrderManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderManagementSystem.Entity.User;
import com.example.OrderManagementSystem.Repo.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	public User createUser(User user) {
		return userRepo.save(user);
		}
    public List<User> getAllUsers() {
    	return userRepo.findAll(); 
    	}
    public User getUserById(String id) {
    	Optional<User> user = userRepo.findById(id);
    	return user.orElse(null); // Return null if not found 
    	} 
    public User updateUser(String id, User user)  { 
    	if (userRepo.existsById(id)) { 
    	user.setId(id); 
    	return userRepo.save(user);
    	} 
    	return null; // Return null if not found
    	} public boolean deleteUser(String id) { 
    		if (userRepo.existsById(id)) {
    			userRepo.deleteById(id); 
    			return true;
    			}
    		return false; // Return false if not found }
    }
    public Optional<User> getUserByEmail(String email) {
    	return userRepo.findByEmail(email);
    	}

}
