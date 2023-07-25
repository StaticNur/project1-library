package ru.portfolio.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.portfolio.library.models.Book;
import ru.portfolio.library.models.Person;
import ru.portfolio.library.repositories.BookRepository;
import ru.portfolio.library.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    private final BookRepository bookRepository;
    @Autowired
    public PersonService(PersonRepository personRepository,BookRepository bookRepository){
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }
    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Person findById(int id){
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }
    @Transactional
    public void savePerson(Person person){
        personRepository.save(person);
    }
    @Transactional
    public void update(int id,Person person){
        /*Person originPerson = personRepository.findById(id).orElse(null);
        originPerson.setFullName(person.getFullName());
        originPerson.setYearOfBirth(person.getYearOfBirth());*/
        person.setId(id);
        personRepository.save(person);
    }
    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }
    public List<Person> findByFullName(String name){
        return personRepository.findByFullName(name);
    }
    public List<Book> personHaveBooks(int fkId){
        return bookRepository.findByFkId(fkId);
    }
}
