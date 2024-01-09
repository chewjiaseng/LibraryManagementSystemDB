package com.example.LibraryManagementSystem.entities;

import javax.persistence.*;

@Entity
@Table(name = "chapter")
public class Chapter {

    @Id
    private Integer chapterId;

    private String chapterName;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    public Chapter(){}
    public Chapter(Integer chapterId, String chapterName, Book book) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.book = book;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    // Constructors, getters, setters
}

