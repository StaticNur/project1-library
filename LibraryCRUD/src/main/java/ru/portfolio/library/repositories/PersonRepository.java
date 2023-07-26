package ru.portfolio.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.portfolio.library.models.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    List<Person> findByFullName(String name);
}
