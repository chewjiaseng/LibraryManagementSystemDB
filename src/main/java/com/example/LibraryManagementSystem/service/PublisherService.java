package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.entities.Book;
import com.example.LibraryManagementSystem.entities.Category;
import com.example.LibraryManagementSystem.entities.Publisher;
import com.example.LibraryManagementSystem.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookService bookService;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    // Additional methods if needed
    public void savePublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public void deletePublisher(Integer id) {
        Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
        if (optionalPublisher.isPresent()) {
            Publisher publisher = optionalPublisher.get();

            // Handle associated books
            List<Book> books = publisher.getBooks();
            for (Book book : books) {
                // Set the publisher to null
                book.setPublisher(null);
                // Save the updated book
                bookService.saveBook(book);
            }

            // Delete the publisher
            publisherRepository.deleteById(id);
        }
    }

    public Publisher getPublisherById(Integer publisherId) {
        Optional<Publisher> optionalPublisher = publisherRepository.findById(publisherId);
        return optionalPublisher.orElse(null);
    }
}

