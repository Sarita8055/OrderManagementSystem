package com.example.OrderManagementSystem.controller;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses; 
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OrderManagementSystem.Entity.Order;
import com.example.OrderManagementSystem.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {
	
	@Autowired private OrderService orderService;
	@Operation(summary = "Create a new order")
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order order) { 
		Order createdOrder = orderService.createOrder(order); 
		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED); 
		}
	
	@Operation(summary = "Get all orders")
	@GetMapping 
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders = orderService.getAllOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
		} 
	@Operation(summary = "Get order by ID") 
		@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Order retrieved successfully"), @ApiResponse(responseCode = "404", description = "Order not found") }) 
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOrderById( @Parameter(description = "ID of the order to be retrieved") @PathVariable String id) { 
		Order order = orderService.getOrderById(id);
		if (order == null) { 
			Map<String, String> errorResponse = Map.of("message", "Order not found"); 
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); 
			}
		return new ResponseEntity<>(order, HttpStatus.OK); 
		} 
	@Operation(summary = "Update order by ID")
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable String id, @RequestBody Order order) {
		Order updatedOrder = orderService.updateOrder(id, order); 
		if (updatedOrder != null) {
			return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
			} 
		Map<String, String> errorResponse = Map.of("message", "Order not found");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	
	@Operation(summary = "Delete order by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable String id) {
		boolean isDeleted = orderService.deleteOrder(id); 
		if (isDeleted) {
			return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK); 
			}
		Map<String, String> errorResponse = Map.of("message", "Order not found");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); }

}
