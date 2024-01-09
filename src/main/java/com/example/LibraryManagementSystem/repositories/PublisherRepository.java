package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    // Additional query methods if needed
}

