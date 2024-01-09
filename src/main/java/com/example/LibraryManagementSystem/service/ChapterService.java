package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entities.Chapter;
import com.example.LibraryManagementSystem.repositories.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    // Additional methods if needed
}

