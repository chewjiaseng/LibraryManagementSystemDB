package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entities.Publisher;
import com.example.LibraryManagementSystem.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    // Additional methods if needed
    public void savePublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }
}

