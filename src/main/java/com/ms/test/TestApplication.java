package com.ms.test;

import com.ms.test.model.Book;
import com.ms.test.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
    public CommandLineRunner demoData(BookRepository repo) {
        return args -> {

            repo.save(new Book(1,"aaa","bbb"));
            repo.save(new Book(2,"ccc","ddd"));
            repo.save(new Book(3,"eee","fff"));
            repo.save(new Book(4,"ggg","hhh"));
        };
    }
}
