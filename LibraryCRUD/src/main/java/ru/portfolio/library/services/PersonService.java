package ru.portfolio.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.portfolio.library.models.Person;
import ru.portfolio.library.repositories.PersonRepository;

import java.util.List;

@Component
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    public List<Person> findAll(){
        return personRepository.findAll();
    }
}
