package com.example.LibraryManagementSystem.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_sequence")
    @SequenceGenerator(name = "publisher_sequence", sequenceName = "PUBLISHER_SEQ", allocationSize = 1)
    @Column(name = "publisher_id")
    private Integer publisherId;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    private String publisherName;

    public Publisher(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Publisher(){}
    public Publisher(Integer publisherId, String publisherName) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public List<Book> getBooks() {
        return books;
    }
    // Constructors, getters, setters
}

