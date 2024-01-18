package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    // Additional query methods if needed
    @Override
    <S extends Publisher> S save(S entity);
}

