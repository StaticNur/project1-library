package ru.portfolio.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.portfolio.library.model.Book;
import ru.portfolio.library.model.Person;
import java.util.List;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> showAll(){
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }
    public Book showBook(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class)).
                stream().findAny().orElse(null);
    }
    public void saveBook(Book book){
        jdbcTemplate.update("INSERT INTO Book(name, author, yearofpublishing,fk_id) VALUES(?,?,?,?)",book.getName(),book.getAuthor(),
                book.getYearOfPublishing(),null);
    }
    public Person personTakeBook(int id){
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.fk_id=Person.id WHERE Book.id=?",
                new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public void update(int id, Integer selectedPersonId){
        jdbcTemplate.update("UPDATE Book set fk_id=? WHERE id=?",selectedPersonId,id);
    }
    public void update(int id){
        jdbcTemplate.update("UPDATE Book set fk_id=? WHERE id=?",null,id);
    }
    public void editBook(int id, Book book){
        jdbcTemplate.update("UPDATE Book set name=?,author=?,yearofpublishing=? WHERE id=?",
                book.getName(),book.getAuthor(),book.getYearOfPublishing(),id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?",id);
    }
}
