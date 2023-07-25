package ru.portfolio.library.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.portfolio.library.models.Person;
import ru.portfolio.library.services.PersonService;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    public PersonValidator(PersonService personService) {this.personService = personService;}

    @Override
    public boolean supports(Class<?> aClass) {
        return personService.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (!(personService.findByFullName(person.getFullName()).isEmpty()))
            errors.rejectValue("fullName","","Это имя уже занято");
    }
}
