package ru.portfolio.library.services;

import org.hibernate.Hibernate;
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
public class BookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    @Autowired
    public BookService(BookRepository bookRepository,PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public Book findById(int id){
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }
    @Transactional
    public void saveBook(Book book){
        bookRepository.save(book);
    }
    @Transactional
    public void update(int id,Book book){
        Book originBook = bookRepository.findById(id).orElse(null);
        originBook.setName(book.getName());
        originBook.setAuthor(book.getAuthor());
        originBook.setYearOfPublishing(book.getYearOfPublishing());
        bookRepository.save(originBook);
    }
    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }
    public Person getBookOwner(int id){
        Book book = bookRepository.findById(id).orElse(null);
        if(book.getFkId() == null)//проверка на существует ли владелец
            return null;
        return personRepository.findById(book.getFkId()).orElse(null);//извлекаем владельца
    }
    @Transactional
    public void assignBook(int id, int selectedPersonId){
        Book book = bookRepository.findById(id).orElse(null);
        book.setFkId(selectedPersonId);
        bookRepository.save(book);
    }
    @Transactional
    public void releaseBook(int id){
        Book book = bookRepository.findById(id).orElse(null);
        book.setFkId(null);
        bookRepository.save(book);
    }
}
