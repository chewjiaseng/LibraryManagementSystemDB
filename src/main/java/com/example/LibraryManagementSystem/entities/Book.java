package com.example.LibraryManagementSystem.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    private Integer bookId;

    private String bookName;
    private Integer chapterNo;
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "publisherId")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Book(){}
    public Book(Integer bookId, String bookName, Integer chapterNo, Date publicationDate, Publisher publisher, Category category) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.chapterNo = chapterNo;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.category = category;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(Integer chapterNo) {
        this.chapterNo = chapterNo;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
// Constructors, getters, setters
}

