package ru.portfolio.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.portfolio.library.models.Book;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    //private BookDAO bookDAO;
    //private PersonDAO personDAO;
    /*@Autowired
    public BookController(BookDAO bookDAO,PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }
    @GetMapping
    public String showAll(Model model){
        model.addAttribute("books",bookDAO.showAll());
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
        bookDAO.saveBook(book);
        return "redirect: /books";
    }
    @GetMapping("{id}")
    public String showBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookDAO.showBook(id));
        model.addAttribute("person",bookDAO.personTakeBook(id));
        model.addAttribute("people",personDAO.showAll());//для выпадающего списка
        return "books/showBook";
    }
    @PatchMapping("{id}")
    public String assignBook(@PathVariable("id") int id,
                             @RequestParam(value = "selectedPersonId" , required = false) Integer selectedPersonId){
        if(selectedPersonId == null){
            bookDAO.update(id);//книга занята, значить освобождаем
            return "redirect: /books/"+id;
        }else{
            bookDAO.update(id, selectedPersonId);//книга свободно, отдаем кому нибудь
        }
        return "redirect: /books";
    }
    @GetMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookDAO.showBook(id));
        return "books/editBook";
    }
    @PatchMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/editBook";
        bookDAO.editBook(id,book);
        return "redirect: /books";
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect: /books";
    }*/
}
