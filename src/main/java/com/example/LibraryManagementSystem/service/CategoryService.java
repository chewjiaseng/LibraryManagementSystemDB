package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entities.Book;
import com.example.LibraryManagementSystem.entities.Category;
import com.example.LibraryManagementSystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookService bookService;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {return categoryRepository.save(category);}

    @Transactional
    public void deleteCategoryById(Integer categoryId) {
        // Fetch the category and its associated books
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Set the category of each book to null
        List<Book> books = category.getBooks();
        if (books != null) {
            books.forEach(book -> book.setCategory(null));

            // Update the books in the database
            bookService.saveAllBooks(books);
        }

        // Delete the category
        categoryRepository.deleteById(categoryId);
    }

}

