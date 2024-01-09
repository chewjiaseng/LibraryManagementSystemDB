package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entities.Book;
import com.example.LibraryManagementSystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Additional methods if needed
}

