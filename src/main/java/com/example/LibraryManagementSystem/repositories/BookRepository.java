package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

