package ru.portfolio.library.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.portfolio.library.dao.PersonDAO;
import ru.portfolio.library.model.Person;
@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    public PersonValidator(PersonDAO personDAO) {this.personDAO = personDAO;}

    @Override
    public boolean supports(Class<?> aClass) {
        return personDAO.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (personDAO.showPerson(person.getFullName())!=null)
            errors.rejectValue("fullName","","Это имя уже занято");
    }
}
