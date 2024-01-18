//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.LibraryManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
        name = "book"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {
    @GeneratedValue
    @Id
    private Integer bookId;
    private String bookName;
    private Integer chapterNo;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    private Date publicationDate;
    @ManyToOne(
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(
            name = "publisherId"
    )
    private Publisher publisher;
    @ManyToOne(
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(
            name = "category_id"
    )
    private Category category;

    public Book() {
    }

    public Book(Integer bookId, String bookName, Integer chapterNo, Date publicationDate, Publisher publisher, Category category) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.chapterNo = chapterNo;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.category = category;
    }

    public Integer getBookId() {
        return this.bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getChapterNo() {
        return this.chapterNo;
    }

    public void setChapterNo(Integer chapterNo) {
        this.chapterNo = chapterNo;
    }

    public Date getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
