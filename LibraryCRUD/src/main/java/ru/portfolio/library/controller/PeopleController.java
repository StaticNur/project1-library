package ru.portfolio.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.portfolio.library.dao.PersonDAO;
import ru.portfolio.library.model.Person;
import ru.portfolio.library.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonDAO personDAO;
    private PersonValidator personValidator;
    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }
    @GetMapping
    public String showAll(Model model){
        model.addAttribute("people", personDAO.showAll());
        return "people/showAll";
    }
    @GetMapping("{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.showPerson(id));
        model.addAttribute("books",personDAO.personHaveBooks(id));
        return "people/showPerson";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }
    @PostMapping
    public String creatPerson(@ModelAttribute("person") @Valid Person person,
                              BindingResult bindingResult){
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())
            return "people/new";
        personDAO.savePerson(person);
        return "redirect: /people";
    }
    @GetMapping("{id}/edit")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.showPerson(id));
        return "people/editPerson";
    }
    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id,@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())
            return "people/editPerson";
        personDAO.update(id,person);
        return "redirect: /people";
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect: /people";
    }

}
