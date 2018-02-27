package com.ms.test.repository;

import com.ms.test.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Book, Long> {
}
