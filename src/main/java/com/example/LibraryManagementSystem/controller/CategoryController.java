package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.entities.Book;
import com.example.LibraryManagementSystem.entities.Category;
import com.example.LibraryManagementSystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String showCategory(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "category";
    }
    @GetMapping("/addCategory")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "add_category";
    }

    @PostMapping("add")
    public String addNewCategory(Category category, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "add_category";
        }
        categoryService.saveCategory(category);
        return "redirect:list";
    }

    @DeleteMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return "redirect:/category/list";  // Redirect to the category page after deletion
    }

    @GetMapping("/edit/{categoryId}")
    public String editCategory(@PathVariable Integer categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", category);
        return "editCategory";
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("category") Category category) {
        // Fetch the existing category from the database using category.getCategoryId()
        Category existingCategory = categoryService.getCategoryById(category.getCategoryId());

        // Update only the category name
        existingCategory.setCategoryName(category.getCategoryName());

        // Save the updated category back to the database
        categoryService.saveCategory(existingCategory);

        return "redirect:/category/list";
    }
}