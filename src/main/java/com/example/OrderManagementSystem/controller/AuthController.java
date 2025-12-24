package com.example.OrderManagementSystem.controller;


import com.example.OrderManagementSystem.Entity.LoginRequest;
import com.example.OrderManagementSystem.Entity.User;
import com.example.OrderManagementSystem.Repo.UserRepo;
import com.example.OrderManagementSystem.service.AuthService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String role = user.getRoles().get(0);
        return authService.generateToken(user.getEmail(), role);
    }
}
