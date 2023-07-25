package ru.portfolio.library.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.portfolio.library.models.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByName(String name);
    List<Book> findByAuthor(String author);
    List<Book> findByFkId(int fkId);
    Page<Book> findAll(Pageable pageable);
    List<Book> findAll(Sort sort);
    List<Book> findByNameStartingWith(String name);
}
