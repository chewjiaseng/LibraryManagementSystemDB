package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.entities.Category;
import com.example.LibraryManagementSystem.entities.Publisher;
import com.example.LibraryManagementSystem.service.CategoryService;
import com.example.LibraryManagementSystem.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/list")
    public String showPublisher(Model model) {
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("publishers", publishers);
        return "publisher";
    }

    @GetMapping("/addPublisher")
    public String showAddPublisherForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "add_publisher";
    }

    @PostMapping("add")
    public String addNewPublisher(Publisher publisher, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "add_publisher";
        }
        publisherService.savePublisher(publisher);
        return "redirect:list";
    }
}
