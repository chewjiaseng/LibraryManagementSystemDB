package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.entities.Book;
import com.example.LibraryManagementSystem.entities.Category;
import com.example.LibraryManagementSystem.entities.Chapter;
import com.example.LibraryManagementSystem.entities.Publisher;
import com.example.LibraryManagementSystem.service.BookService;
import com.example.LibraryManagementSystem.service.CategoryService;
import com.example.LibraryManagementSystem.service.ChapterService;
import com.example.LibraryManagementSystem.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<Publisher> publishers = publisherService.getAllPublishers();
        List<Book> books = bookService.getAllBooks();
        List<Chapter> chapters = chapterService.getAllChapters();

        model.addAttribute("categories", categories);
        model.addAttribute("publishers", publishers);
        model.addAttribute("books", books);
        model.addAttribute("chapters", chapters);

        return "dashboard";
    }

        @GetMapping("/editBook/{id}")
        public String showEditBookPage(@PathVariable Long id, Model model) {
            // Retrieve the book by ID from the database
            Book book = bookService.getBookById(id);

            // Add the book to the model
            model.addAttribute("book", book);

            return "editBook";
        }

    @PostMapping("/editBook")
    public String editBook(@ModelAttribute("book") Book updatedBook, Model model) {
        // Save the publisher first
        publisherService.savePublisher(updatedBook.getPublisher());

        // Now you can save the book
        bookService.updateBook(updatedBook);

        // Retrieve the updated list of books
        List<Book> updatedBooks = bookService.getAllBooks();
        model.addAttribute("books", updatedBooks);

        // Return the dashboard page
        return "redirect:/dashboard";
    }
}

