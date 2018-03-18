package com.ms.library.controller;

import com.ms.library.model.Book;
import com.ms.library.model.Category;
import com.ms.library.model.Role;
import com.ms.library.model.User;
import com.ms.library.repository.BookRepository;
import com.ms.library.repository.CategoryRepository;
import com.ms.library.repository.RoleRepository;
import com.ms.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class indexController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/")
    String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("uzytkownikZalogowany", name);
        return "index";
    }

    @RequestMapping("/login")
    String login() {
        return "login";
    }

    @RequestMapping("/wyloguj")
    String wyloguj(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        return "redirect:login";
    }

    @RequestMapping("/listaksiazek")
    String lista(Model model) {
        model.addAttribute("lista", bookRepository.findAll());
        model.addAttribute("liczba", bookRepository.count());//po co?
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "listaksiazek";
    }

    @RequestMapping("/listakategorii")
    String listakateg(Model model) {
        model.addAttribute("listakat", categoryRepository.findAll());
        model.addAttribute("liczbakat", categoryRepository.count());
        return "listakategorii";
    }

    @RequestMapping("/ksiazkadetal/{id}")
    String listaId(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("lista", bookRepository.getOne(id));
        model.addAttribute("liczba", bookRepository.count());
        return "ksiazkadetal";
    }

    @RequestMapping(value = "/dodajkategorieform", method = RequestMethod.GET)
    String dodajkat(Model model) {
        model.addAttribute("category", new Category());
        return "dodajkategorieform";
    }


    @RequestMapping(value = "/potwierdzkat", method = RequestMethod.POST)
    String dodajjjjkat(@ModelAttribute Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/error";
        }
        categoryRepository.save(new Category(category.getName()));
        return "redirect:/listakategorii";
    }

    @RequestMapping(value = "/dodajksiazkaform", method = RequestMethod.GET)
    String dodaj(Model model) {
        model.addAttribute("listakat", categoryRepository.findAll());
        model.addAttribute("book", new Book());
        return "dodajksiazkaform";
    }

    @RequestMapping(value = "/potwierdz", method = RequestMethod.POST)
    String dodajjjj(@ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/error";
        }
        bookRepository.save(new Book(book.getBookAutorImie(), book.getBookAutorNazwisko(), book.getBookTytul(), book.getBookWydawnictwo(), book.getBookRokWydania()));
        return "redirect:/listaksiazek";
    }

//-------------------------------


    @RequestMapping(value = "/dodajuzytkownikaform", method = RequestMethod.GET)
    String dodajuzytkownika(Model model) {
        model.addAttribute("uzytkownik", new User());
        return "dodajuzytkownikaform";
    }

    @RequestMapping(value = "/potwierdzdu", method = RequestMethod.POST)
    String potwierdzdu(@ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/error";
        }
        userRepository.save(new User(user.getUserName(), user.getUserPassword(), user.getUserRole(), user.getEnabled()));
        return "redirect:/listauzytkownikow";
    }

    @RequestMapping("/listauzytkownikow")
    String listauzytkownikow(Model model) {
        model.addAttribute("listau", userRepository.findAll());
        model.addAttribute("liczbauzytkownikow", userRepository.count());
        return "listauzytkownikow";
    }


    @RequestMapping("/uzytkownikdetal/{id}")
    String uzytkownikdetal(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("uzytkownik", userRepository.getOne(id));
        model.addAttribute("liczbauzytkownikow", userRepository.count());
        return "uzytkownikdetal";
    }

    @RequestMapping(value = "/dodajroledouzytkownika/{id}", method = RequestMethod.GET)
    String dodajkategoriedouzytkownika(@PathVariable("id") long id, Model model) {
        model.addAttribute("uzytkownik", userRepository.findOne(id));
        model.addAttribute("rola", roleRepository.findAll());
        return "dodajroledouzytkownika";
    }

    @RequestMapping(value = "/dodajroledouzytkownikapotwierdz/{id}", method = RequestMethod.GET)
    String dodajroledouzytkownikapotwierdz(@PathVariable("id") long id, @RequestParam long rola, Model model) {
        model.addAttribute("uzytkownik", userRepository.findOne(id));
        model.addAttribute("rola", roleRepository.findAll());

        Role role = roleRepository.findOne(rola);
        User user = userRepository.findOne(id);
        user.getRoles().add(role);
        user.setUserRole(role.getRoleName());

        Role newuserrole = new Role();
        newuserrole.setUserName(user.getUserName());
        newuserrole.setRoleName(user.getUserRole());
        roleRepository.save(newuserrole);



        //role.setRoleName(role);
      //  book.getCategories().add(category);
        userRepository.save(user);
        System.out.println(user.toString());
       // bookRepository.save(book);
        return "redirect:/listauzytkownikow";
    }







    //dodawanie kategorii do ksiazki
    @RequestMapping(value = "/dodajkategoriedoksiazki/{id}", method = RequestMethod.GET)
    String dodajkategoriedoksiazki(@PathVariable("id") long id, Model model) {
        model.addAttribute("ksiazka", bookRepository.findOne(id));
        model.addAttribute("kategoria", categoryRepository.findAll());
        return "dodajkategoriedoksiazki";
    }

    @RequestMapping(value = "/dodajkategoriedoksiazkipotwierdz/{id}", method = RequestMethod.GET)
    String dodajkategoriedoksiazki2(@PathVariable("id") long id, @RequestParam long kategoria, Model model) {
        model.addAttribute("ksiazka", bookRepository.findOne(id));
        model.addAttribute("kategoria", categoryRepository.findAll());
        Category category = categoryRepository.findOne(kategoria);
        Book book = bookRepository.findOne(id);
        book.getCategories().add(category);
        bookRepository.save(book);
        return "redirect:/listaksiazek";
    }
}

