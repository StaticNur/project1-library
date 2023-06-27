package ru.portfolio.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.portfolio.library.model.Book;
import ru.portfolio.library.model.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> showAll(){
        return jdbcTemplate.query("SELECT * FROM Person",new BeanPropertyRowMapper<>(Person.class));
    }
    public Person showPerson(String fullName){//для валидации, уникальность в бд
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullName=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public Person showPerson(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    /*public Book personHaveBooks(int id){
        return jdbcTemplate.query("SELECT Book.* FROM Person INNER JOIN Book ON Person.fk_id = Book.id WHERE Person.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }*/
    public List<Book> personHaveBooks(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE Book.id=?",
                new Object[]{showPerson(id).getFk_id()}, new BeanPropertyRowMapper<>(Book.class));
    }
    public void savePerson(Person person){
        jdbcTemplate.update("INSERT INTO Person(fullName,yearOfBirth) VALUES (?,?)", person.getFullName(),person.getYearOfBirth());
    }
}
