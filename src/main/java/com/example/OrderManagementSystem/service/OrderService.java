package com.example.OrderManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderManagementSystem.Entity.Order;
import com.example.OrderManagementSystem.Repo.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	public Order createOrder(Order order) {
		return orderRepo.save(order); 
		}
    public List<Order> getAllOrders() {
    	return orderRepo.findAll(); 
    	}
    public Order getOrderById(String id) {
    	Optional<Order> order = orderRepo.findById(id); 
    return order.orElse(null); // Return null if not found }
    }
    public Order updateOrder(String id, Order order) { 
    	if (orderRepo.existsById(id)) {
    		order.setId(id); 
    		return orderRepo.save(order);
    		} 
    	return null; // Return null if not found }
    }
    public boolean deleteOrder(String id) { 
    	if (orderRepo.existsById(id)) { 
    		orderRepo.deleteById(id); 
    		return true;
    		}
    	return false; // Return false if not found }
    }

}
