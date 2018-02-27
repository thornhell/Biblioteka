package com.ms.library.controller;

import com.ms.library.model.Book;
import com.ms.library.model.Category;
import com.ms.library.repository.BookRepository;
import com.ms.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class indexController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/listaksiazek")
    String lista(Model model) {
        model.addAttribute("lista", bookRepository.findAll());
        model.addAttribute("liczba", bookRepository.count());
        return "listaksiazek";
    }

    @RequestMapping("/listakategorii")
    String listakateg(Model model) {
        model.addAttribute("listakat", categoryRepository.findAll());
        model.addAttribute("liczbakat", categoryRepository.count());
        return "listakategorii";
    }

    @RequestMapping("/listaksiazek/{id}")
    String listaId(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("lista", bookRepository.getOne(id));
        model.addAttribute("liczba", bookRepository.count());
        return "listaksiazek";
    }

    @RequestMapping(value = "/dodajkategorie", method = RequestMethod.GET)
    String dodajkat(Model model) {
        model.addAttribute("category", new Category());
        return "addcategoryform";
    }


    @RequestMapping(value = "/potwierdzkat", method = RequestMethod.POST)
    String dodajjjjkat(@ModelAttribute Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/error";
        }
        categoryRepository.save(new Category(category.getName()));
        return "redirect:/listakategorii";
    }

    @RequestMapping(value = "/dodajksiazke", method = RequestMethod.GET)
    String dodaj(Model model) {

        model.addAttribute("book", new Book());
        return "addksiazkaform";
    }

    @RequestMapping(value = "/potwierdz", method = RequestMethod.POST)
    String dodajjjj(@ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/error";
        }
        bookRepository.save(new Book(book.getBookAuthor(), book.getBookTitle()));
        return "redirect:/listaksiazek";
    }
}
