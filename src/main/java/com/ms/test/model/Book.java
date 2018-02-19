package com.ms.test.model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookid")
    private long bookId;

    @Column(name = "bookauthor")
    private String bookAuthor;

    @Column(name = "bookname")
    private String bookTitle;

    public Book() {
    }

    ;

    public Book(long bookId, String bookAuthor, String bookTitle) {
        this.bookId = bookId;
        this.bookAuthor = bookAuthor;
        this.bookTitle = bookTitle;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
