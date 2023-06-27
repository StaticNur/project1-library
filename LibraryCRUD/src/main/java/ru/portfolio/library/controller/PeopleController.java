package ru.portfolio.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.portfolio.library.annotation.CustomSize;
import ru.portfolio.library.dao.PersonDAO;
import ru.portfolio.library.model.Person;
import ru.portfolio.library.util.CustomSizeValidator;
import ru.portfolio.library.util.PersonValidator;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonDAO personDAO;
    private CustomSizeValidator customSizeValidator;
    private PersonValidator personValidator;
    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator,CustomSizeValidator customSizeValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
        this.customSizeValidator = customSizeValidator;
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
    @PostMapping()
    public String creatPerson(@ModelAttribute("person") @Valid Person person,
                              BindingResult bindingResult, ConstraintValidatorContext context){
        if (customSizeValidator.isValid(person.getYearOfBirth(), context))
            return "people/new";

        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())
            return "people/new";
        personDAO.savePerson(person);
        return "redirect: people";
    }
}
