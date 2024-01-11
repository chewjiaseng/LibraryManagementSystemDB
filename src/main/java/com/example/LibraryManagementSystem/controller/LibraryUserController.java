package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.entities.LibraryUser;
import com.example.LibraryManagementSystem.service.LibraryUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class LibraryUserController {
    private final LibraryUserService userService;

    @Autowired
    public LibraryUserController(LibraryUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, @RequestParam String role, Model model) {
        // Fetch the user from the database based on the user ID
        LibraryUser user = userService.getUserByEmail(email);

        // Check if the user exists and the provided password and role are correct
        if (user != null && user.getPassword().equals(password) && user.getRole().equals(role)) {
            // Redirect to the appropriate dashboard based on the role
            if ("admin".equals(role)) {
                return "redirect:/admin-dashboard";
            } else {
                return "redirect:/dashboard";
            }
        } else {
            // Invalid login, add error message and redirect back to the login page
            model.addAttribute("error", "Wrong username, password, or role!");
            return "login";
        }
    }

    @GetMapping("/user-dashboard")
    public String showDashboard() {
        return "dashboard";
    }


    @GetMapping("/admin-dashboard")
    public String showAdminDashboard() {
        return "admin_dashboard";
    }
}
