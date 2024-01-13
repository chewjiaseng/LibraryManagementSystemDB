package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.entities.Book;
import com.example.LibraryManagementSystem.entities.Category;
import com.example.LibraryManagementSystem.entities.Publisher;
import com.example.LibraryManagementSystem.service.BookService;
import com.example.LibraryManagementSystem.service.CategoryService;
import com.example.LibraryManagementSystem.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<Publisher> publishers = publisherService.getAllPublishers();
        List<Book> books = bookService.getAllBooks();

        model.addAttribute("categories", categories);
        model.addAttribute("publishers", publishers);
        model.addAttribute("books", books);

        return "dashboard";
    }

    @GetMapping("/addBook")
    public String showAddBookForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<Publisher> publishers = publisherService.getAllPublishers();

        model.addAttribute("book", new Book());
        model.addAttribute("categories", categories);
        model.addAttribute("publishers", publishers);
        return "add_book";
    }

    @PostMapping("add")
    public String addNewBook(Book book, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "add_book";
        }
        bookService.saveBook(book);
        return "redirect:dashboard";
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

