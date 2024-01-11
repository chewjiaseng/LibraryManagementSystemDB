package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entities.Book;
import com.example.LibraryManagementSystem.entities.Chapter;
import com.example.LibraryManagementSystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer bookId) {
        return bookRepository.findById(bookId);
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
