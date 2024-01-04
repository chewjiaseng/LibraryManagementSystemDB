package com.example.LibraryManagementSystem.service;


import com.example.LibraryManagementSystem.entities.LibraryUser;
import com.example.LibraryManagementSystem.repositories.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryUserService {

    private final LibraryUserRepository userRepository;

    @Autowired
    public LibraryUserService(LibraryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<LibraryUser> getAllUsers() {
        return userRepository.findAll();
    }

    public LibraryUser getUserById(Long userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }


    public LibraryUser saveUser(LibraryUser user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

