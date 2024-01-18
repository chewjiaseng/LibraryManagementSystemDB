package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entities.Book;
import com.example.LibraryManagementSystem.entities.Category;
import com.example.LibraryManagementSystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void updateBook(Book updatedBook) {
        // Check if the book with the given ID exists
        if (bookRepository.existsById(updatedBook.getBookId())) {
            // Retrieve the existing book from the database
            Book existingBook = bookRepository.findById(updatedBook.getBookId()).orElseThrow();

            // Update the book details using setters
            existingBook.setBookName(updatedBook.getBookName());
            existingBook.setPublisher(updatedBook.getPublisher());
            existingBook.setChapterNo(updatedBook.getChapterNo());
            existingBook.setPublicationDate(updatedBook.getPublicationDate());
            existingBook.getCategory().setCategoryName(updatedBook.getCategory().getCategoryName());

            // Save the updated book
            bookRepository.save(existingBook);
        } else {
            // Handle the case where the book with the given ID doesn't exist
            throw new RuntimeException("Book not found with ID: " + updatedBook.getBookId());
        }
    }

    public Book saveBook(Book book) {return bookRepository.save(book);}

    public void saveAllBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    public Book getBookById(Long id) {
        // Retrieve a book by its ID using findById method
        return bookRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
    }

    @Transactional
    public void deleteBookById(Integer bookId) {
        System.out.println("Deleting book with ID: " + bookId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        bookOptional.ifPresentOrElse(
                bookToDelete -> bookRepository.delete(bookToDelete),
                () -> {
                    throw new EntityNotFoundException("Book with ID " + bookId + " not found.");
                }
        );
    }

    // Additional methods if needed
}