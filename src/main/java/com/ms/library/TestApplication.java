package com.ms.library;

import com.ms.library.model.Book;
import com.ms.library.model.Category;
import com.ms.library.model.Role;
import com.ms.library.model.User;
import com.ms.library.repository.BookRepository;
import com.ms.library.repository.CategoryRepository;
import com.ms.library.repository.RoleRepository;
import com.ms.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class TestApplication implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAllInBatch();
        categoryRepository.deleteAllInBatch();
        roleRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        bookRepository.save(new Book("Jan", "Kowalski", "Ważna książka", "Ossolineum", 1977));
        bookRepository.save(new Book("Adam", "Nowak", "Kolejna ważna książka", "PWN", 1988));

        categoryRepository.save(new Category("Naukowa"));
        categoryRepository.save(new Category("Kryminał"));
        categoryRepository.save(new Category("Horror"));

        User u1 = new User("admin", "admin123", "ADMIN",1);
        User u2 = new User("biblio", "biblio123", "BIBLIO",1);
        User u3 = new User("user", "user123", "USER",1);
        Role r_admin = new Role("admin", "ADMIN");
        Role r_biblio = new Role("biblio", "BIBLIO");
        Role r_user = new Role("user", "USER");

        Set<Role> rolesAdmin = new HashSet<Role>();
        rolesAdmin.add(r_admin);
        rolesAdmin.add(r_biblio);
        rolesAdmin.add(r_user);
        u1.setRoles(rolesAdmin);

        Set<Role> rolesBilio = new HashSet<Role>();
        rolesBilio.add(r_biblio);
        rolesBilio.add(r_user);
        u2.setRoles(rolesBilio);

        Set<Role> rolesUser = new HashSet<Role>();
        rolesUser.add(r_user);
        u3.setRoles(rolesUser);

        roleRepository.save(rolesAdmin);
        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
    }
}
