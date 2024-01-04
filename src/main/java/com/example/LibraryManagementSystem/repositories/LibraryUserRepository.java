package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.entities.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {
    Optional<LibraryUser> findByUserId(Long userId);
}