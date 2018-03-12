package com.ms.library;

import com.ms.library.model.Book;
import com.ms.library.model.Category;
import com.ms.library.repository.BookRepository;
import com.ms.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class TestApplication implements CommandLineRunner{

    @Autowired
    BookRepository bookRepository;

	@Autowired
    CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        //del
        bookRepository.deleteAllInBatch();
        categoryRepository.deleteAllInBatch();

        // new objects

        bookRepository.save(new Book("Jan","Kowalski","Ważna książka", "Ossolineum", 1977));
        bookRepository.save(new Book("Adam","Nowak","Kolejna ważna książka", "PWN", 1988));

        categoryRepository.save(new Category("Naukowa"));
        categoryRepository.save(new Category("Kryminał"));
        categoryRepository.save(new Category("Horror"));


//        List<Book> bookList = new ArrayList<>();
//        bookList.add(b1);
//        bookList.add(b2);

        Set<Category> categoryList = new HashSet<Category>();

//        categoryList.add(c1);
//        categoryList.add(c2);
//        categoryList.add(c3);
//
//        //mixing
//        b1.setCategories(categoryList);
//        b2.setCategories(categoryList);
//        c1.setBooks(bookList);
//        c2.setBooks(bookList);
//
//        //save
//        bookRepository.save(b1);
//        bookRepository.save(b2);

    }




}
