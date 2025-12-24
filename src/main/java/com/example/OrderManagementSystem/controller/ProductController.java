package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.Entity.Product;
import com.example.OrderManagementSystem.service.ProductService;

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

@RequestMapping("/product") 
@RestController
@CrossOrigin("*")
public class ProductController {

	@Autowired 
	private ProductService productService;
	
	@Operation(summary = "Create a new product") 	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product createdProduct = productService.createProduct(product);
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED); 
		}
	
	@Operation(summary = "Get all products") 
	@GetMapping 
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts(); 
		return new ResponseEntity<>(products, HttpStatus.OK);
		} 
	
	@Operation(summary = "Get product by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Product retrieved successfully"), @ApiResponse(responseCode = "404", description = "Product not found") })
	@GetMapping("/{id}") 
	public ResponseEntity<Object> getProductById( @Parameter(description = "ID of the product to be retrieved")
	@PathVariable String id) { 
		Product product = productService.getProductById(id); 
		if (product == null) {
			Map<String, String> errorResponse = Map.of("message", "Product not found");
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
			} 
		return new ResponseEntity<>(product, HttpStatus.OK);
		}
	
	@Operation(summary = "Update product by ID")
	@PutMapping("/{id}") 
	public ResponseEntity<Object> updateProduct(@PathVariable String id, @RequestBody Product product) { 
		Product updatedProduct = productService.updateProduct(id, product);
		if (updatedProduct != null) {
			return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
			} 
		Map<String, String> errorResponse = Map.of("message", "Product not found"); 
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); 
		}
	
	@Operation(summary = "Delete product by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable String id) { 
		boolean isDeleted = productService.deleteProduct(id); 
		if (isDeleted) {
			return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
			}
		Map<String, String> errorResponse = Map.of("message", "Product not found");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
