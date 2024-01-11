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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
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

    @GetMapping("/addBook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Category());
        return "add_book";
    }

    @DeleteMapping("/api/books/{bookId}")
    @ResponseBody
    public ResponseEntity<Object> deleteBook(@PathVariable Integer bookId) {
        try {
            bookService.deleteBookById(bookId);
            List<Book> updatedBooks = bookService.getAllBooks();

            // Returning a success message as JSON along with a 200 OK status
            return ResponseEntity.ok().body(updatedBooks);
        } catch (EntityNotFoundException e) {
            // Returning a 404 Not Found status along with an error message
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Returning a 500 Internal Server Error status along with an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting book: " + e.getMessage());
        }
    }



}

