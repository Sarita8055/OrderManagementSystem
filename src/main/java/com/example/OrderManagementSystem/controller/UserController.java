package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.Entity.User;
import com.example.OrderManagementSystem.service.UserService;

import java.util.List;
import java.util.Map; 
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter; 
import io.swagger.v3.oas.annotations.responses.ApiResponses; 
import io.swagger.v3.oas.annotations.responses.ApiResponse;
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	@Autowired 
	private UserService userService;
	
	@Operation(summary = "Create a new user")
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) { 
		User createdUser = userService.createUser(user); 
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
		}
	
	@Operation(summary = "Get all users")
	@GetMapping 
	public ResponseEntity<List<User>> getAllUsers() { 
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
		}
	@Operation(summary = "Get user by ID") 
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User retrieved successfully"), @ApiResponse(responseCode = "404", description = "User not found") }) 
	@GetMapping("/{id}") 
	public ResponseEntity<Object> getUserById( @Parameter(description = "ID of the user to be retrieved", required = true) @PathVariable String id) {
		User user = userService.getUserById(id); 
		if (user == null) { 
			Map<String, String> errorResponse = Map.of("message", "User not found");
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); 
			} 
		return new ResponseEntity<>(user, HttpStatus.OK);
		}
	
	@Operation(summary = "Update user by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User updated successfully"), @ApiResponse(responseCode = "404", description = "User not found") })
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser( @Parameter(description = "ID of the user to be updated", required = true) @PathVariable String id, @RequestBody User user) { 
		User updatedUser = userService.updateUser(id, user); 
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK); 
			}
		Map<String, String> errorResponse = Map.of("message", "User not found");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	
	@Operation(summary = "Delete user by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User deleted successfully"), @ApiResponse(responseCode = "404", description = "User not found") }) 
	@DeleteMapping("/{id}") 
	public ResponseEntity<Object> deleteUser( @Parameter(description = "ID of the user to be deleted", required = true) @PathVariable String id) {
		boolean isDeleted = userService.deleteUser(id); 
		if (isDeleted) {
			return new ResponseEntity<>("User deleted successfully", HttpStatus.OK); 
			} 
		Map<String, String> errorResponse = Map.of("message", "User not found"); 
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}

}
