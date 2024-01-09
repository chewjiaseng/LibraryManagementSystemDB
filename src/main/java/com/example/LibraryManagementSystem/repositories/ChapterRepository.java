package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
    // Additional query methods if needed
}

