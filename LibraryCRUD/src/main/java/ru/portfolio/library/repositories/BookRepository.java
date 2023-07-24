package ru.portfolio.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.portfolio.library.models.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
