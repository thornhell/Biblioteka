package com.ms.test.controller;

import com.ms.test.model.Book;
import com.ms.test.repository.BookRepository;
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

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/lista")
    String lista(Model model) {
        model.addAttribute("lista", bookRepository.findAll());
        model.addAttribute("liczba", bookRepository.count());
        return "lista";
    }

    @RequestMapping("/lista/{id}")
    String listaId(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("lista", bookRepository.getOne(id));
        model.addAttribute("liczba", bookRepository.count());
        return "lista";
    }

    @RequestMapping(value = "/dodaj", method = RequestMethod.GET)
    String dodaj(Model model) {

        model.addAttribute("book", new Book());
        return "addform";
    }

    @RequestMapping(value = "/potwierdz", method = RequestMethod.POST)
    String dodajjjj(@ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/error";
        }
        bookRepository.save(new Book(book.getBookId(), book.getBookAuthor(), book.getBookTitle()));
        return "redirect:/lista";
    }
}
