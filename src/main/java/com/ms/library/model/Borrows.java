package com.ms.library.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "borrows")
public class Borrows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowId")
    private long borrowId;
    @Column(name = "dataWyp")
    @Temporal(TemporalType.DATE)
    private Date dataWyp = new Date();
    @Column(name = "dataZwr", nullable = true)
    private Date dataZwr;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    public Borrows() {
    }
    public Borrows( Date dataZwr, User user, Book book) {
//        this.dataWyp = dataWyp;
        this.dataZwr = dataZwr;
        this.user = user;
        this.book = book;
    }

    public Date getDataWyp() {
        return dataWyp;
    }

    public void setDataWyp(Date dataWyp) {
        this.dataWyp = dataWyp;
    }

    public long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(long borrowId) {
        this.borrowId = borrowId;
    }



    public Date getDataZwr() {
        return dataZwr;
    }

    public void setDataZwr(Date dataZwr) {
        this.dataZwr = dataZwr;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
