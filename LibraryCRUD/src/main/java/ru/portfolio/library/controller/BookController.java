package ru.portfolio.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.portfolio.library.dao.BookDAO;
import ru.portfolio.library.dao.PersonDAO;
import ru.portfolio.library.models.Book;
import ru.portfolio.library.models.Person;
import ru.portfolio.library.services.BookService;
import ru.portfolio.library.services.PersonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BookService bookService;
    private final PersonService personService;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO, BookService bookService, PersonService personService) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookService = bookService;
        this.personService = personService;
    }
    @GetMapping
    public String showAll(Model model){
        model.addAttribute("books",bookService.findAll());
        return "books/showAll";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }
    @PostMapping
    public String creatBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/new";
        bookService.saveBook(book);
        return "redirect: /books";
    }
    @GetMapping("{id}")
    public String showBook(@PathVariable("id") int id, Model model,
                           @ModelAttribute("book") Book book){
        model.addAttribute("book",bookService.findById(id));
        Person bookOwner = bookService.getBookOwner(id);
        if(bookOwner != null)
            model.addAttribute("owner",bookOwner);//есть владелец
        else
            model.addAttribute("people",personService.findAll());//для выпадающего списка
        return "books/showBook";
    }
    @PatchMapping("{id}/release")
    public String releaseBook(@PathVariable("id") int id){
        bookService.releaseBook(id);
        return "redirect: /books/"+id;
    }
    @PatchMapping("{id}/assign")
    public String assignBook(@PathVariable("id") int id,
                             @RequestParam("selectedPersonId") int selectedPersonId){
        bookService.assignBook(id, selectedPersonId);
        return "redirect: /books/"+id;
    }
    @GetMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookService.findById(id));
        return "books/editBook";
    }
    @PatchMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/editBook";
        bookService.update(id,book);
        return "redirect: /books";
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect: /books";
    }
}
