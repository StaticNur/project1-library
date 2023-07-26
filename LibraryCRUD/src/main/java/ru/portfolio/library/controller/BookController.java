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
    private final BookService bookService;
    private final PersonService personService;
    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }
    @GetMapping
    public String showAll(@RequestParam(value = "page", required = false) Integer page,
                          @RequestParam(value = "books_per_page", required = false ) Integer booksPerPage,
                          @RequestParam(value = "sort_by_year", defaultValue = "false") boolean sortByYear,
                          Model model){
        if (page == null || booksPerPage ==null){
            model.addAttribute("books",bookService.findAll(sortByYear));
        }else{
            model.addAttribute("books",bookService.findAll(page,booksPerPage,sortByYear));
        }
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
                           @ModelAttribute("person") Person person){
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
        bookService.assignBook(id, personService.findById(selectedPersonId));
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
    @GetMapping("/search")
    public String searchBook(@RequestParam(value = "name", defaultValue = "") String name, Model model){
        model.addAttribute("books",bookService.findByNameStartingWith(name));
        return "books/searchBook";
    }
}
