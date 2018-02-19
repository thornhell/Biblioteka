package com.ms.test.repository;

import com.ms.test.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> findAllByOrderByBookIdDesc();
}
