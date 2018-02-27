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
import java.util.List;

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

        Book b1 = new Book("book1","book1");
        Book b2 = new Book("book2","book2");

        Category c1 = new Category("category1");
        Category c2 = new Category("category2");
        Category c3 = new Category("category3");


        List<Book> bookList = new ArrayList<>();
        bookList.add(b1);
        bookList.add(b2);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(c1);
        categoryList.add(c2);

        //mixing
        b1.setCategories(categoryList);
        b2.setCategories(categoryList);
        c1.setBooks(bookList);
        c2.setBooks(bookList);

        //save
        bookRepository.save(b1);
        bookRepository.save(b2);

    }




}
