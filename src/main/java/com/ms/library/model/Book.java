package com.ms.library.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private long bookId;
    @Column(name = "bookAutorImie")
    private String bookAutorImie;
    @Column(name = "bookAutorNazwisko")
    private String bookAutorNazwisko;
    @Column(name = "bookTytul")
    private String bookTytul;
    @Column(name = "bookWydawnictwo")
    private String bookWydawnictwo;
    @Column(name = "bookRokWydania")
    private int bookRokWydania;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_category",
            joinColumns = {@JoinColumn(name = "bookId")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})

    private Set<Category> categories = new HashSet<Category>();

    public Book() {
    }

    public Book(String bookAutorImie, String bookAutorNazwisko, String bookTytul, String bookWydawnictwo, int bookRokWydania) {
        this.bookAutorImie = bookAutorImie;
        this.bookAutorNazwisko = bookAutorNazwisko;
        this.bookTytul = bookTytul;
        this.bookWydawnictwo = bookWydawnictwo;
        this.bookRokWydania = bookRokWydania;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookAutorImie() {
        return bookAutorImie;
    }

    public void setBookAutorImie(String bookAutorImie) {
        this.bookAutorImie = bookAutorImie;
    }

    public String getBookAutorNazwisko() {
        return bookAutorNazwisko;
    }

    public void setBookAutorNazwisko(String bookAutorNazwisko) {
        this.bookAutorNazwisko = bookAutorNazwisko;
    }

    public String getBookTytul() {
        return bookTytul;
    }

    public void setBookTytul(String bookTytul) {
        this.bookTytul = bookTytul;
    }

    public String getBookWydawnictwo() {
        return bookWydawnictwo;
    }

    public void setBookWydawnictwo(String bookWydawnictwo) {
        this.bookWydawnictwo = bookWydawnictwo;
    }

    public int getBookRokWydania() {
        return bookRokWydania;
    }

    public void setBookRokWydania(int bookRokWydania) {
        this.bookRokWydania = bookRokWydania;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
