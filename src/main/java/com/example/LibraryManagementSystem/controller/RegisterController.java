package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.entities.LibraryUser;
import com.example.LibraryManagementSystem.service.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final LibraryUserService userService;

    @Autowired
    public RegisterController(LibraryUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping
    public String register(LibraryUser newUser, Model model) {
        // Implement registration logic
        try {
            // Insert the new user into the database
            userService.saveUser(newUser);
            model.addAttribute("message", "Registration successful!");
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed. Please try again.");
        }
        return "register_result";
    }
}
