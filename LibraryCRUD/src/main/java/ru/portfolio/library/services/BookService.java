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
    private final PersonRepository personRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    public List<Book> findAll(int page, int booksPerPage, boolean sortByYear) {
        //http://localhost:8080/books?page=1&books_per_page=3
        //http://localhost:8080/books?sort_by_year=true
        if ((page >= 0) && (booksPerPage > 0) && (sortByYear))//все есть (параметры в поисковой строке)
            return bookRepository.
                    findAll(PageRequest.of(page, booksPerPage,Sort.by("yearOfPublishing"))).getContent();
        else  if ((page == 0) && (booksPerPage >= 0) && (sortByYear)) {//только сортировка
            return bookRepository.findAll(Sort.by("yearOfPublishing"));
        }if ((page >= 0) && (booksPerPage > 0)) {//только страницы
            return bookRepository.findAll(PageRequest.of(page,booksPerPage)).getContent();
        }else return bookRepository.findAll(); //ничего нет
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
        Book originBook = bookRepository.findById(id).orElse(null);
        originBook.setName(book.getName());
        originBook.setAuthor(book.getAuthor());
        originBook.setYearOfPublishing(book.getYearOfPublishing());
        bookRepository.save(originBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Person getBookOwner(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book.getFkId() == null)//проверка на существует ли владелец
            return null;
        return personRepository.findById(book.getFkId()).orElse(null);//извлекаем владельца
    }

    @Transactional
    public void assignBook(int id, int selectedPersonId) {
        Book book = bookRepository.findById(id).orElse(null);
        book.setFkId(selectedPersonId);
        LocalDateTime now = LocalDateTime.now();
        book.setDateOfTakeBook(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
        bookRepository.save(book);
    }

    @Transactional
    public void releaseBook(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        book.setFkId(null);
        book.setDateOfTakeBook(null);
        bookRepository.save(book);
    }
    public List<Book> findByNameStartingWith(String name){
        return bookRepository.findByNameStartingWith(name);
    }
}
