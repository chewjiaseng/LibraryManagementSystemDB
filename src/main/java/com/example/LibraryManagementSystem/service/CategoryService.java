package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entities.Category;
import com.example.LibraryManagementSystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Additional methods if needed
}

