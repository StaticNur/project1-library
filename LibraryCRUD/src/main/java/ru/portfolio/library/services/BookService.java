package ru.portfolio.library.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.portfolio.library.models.Book;
import ru.portfolio.library.models.Person;
import ru.portfolio.library.repositories.BookRepository;
import ru.portfolio.library.repositories.PersonRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> findAll(boolean sortByYear) {
        //http://localhost:8080/books?sort_by_year=true
        if (sortByYear)//только сортировка
            return bookRepository.findAll(Sort.by("yearOfPublishing"));
        else return bookRepository.findAll();
    }
    public List<Book> findAll(int page, int booksPerPage, boolean sortByYear) {
        //http://localhost:8080/books?page=1&books_per_page=3
        if (sortByYear)//все есть (параметры в поисковой строке)
            return bookRepository.findAll(PageRequest.of(page, booksPerPage,Sort.by("yearOfPublishing"))).getContent();
        else return bookRepository.findAll(PageRequest.of(page,booksPerPage)).getContent();//только страницы
    }
    public Book findById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        Book originBook = bookRepository.findById(id).get();
        book.setOwner(originBook.getOwner());
        book.setId(id);
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Person getBookOwner(int id) {
        //save здесь не нужен т.к. объект загружается не лениво. Мы находимся на стороне One
        return bookRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void assignBook(int id, Person selectedPersonId) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setOwner(selectedPersonId);
            book.setDateOfTakeBook(new Date());
        });
    }

    @Transactional
    public void releaseBook(int id) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
            book.setDateOfTakeBook(null);
        });
    }
    public List<Book> findByNameStartingWith(String name){
        return bookRepository.findByNameStartingWith(name);
    }
}
