package ru.portfolio.library.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 1, max = 200, message = "не должен быть пустым")
    @Pattern(regexp = "([А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+)|([A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+)", message = "Ваше полное имя должно быть в таком формате: Иванов Иван Иванович")
    @Column(name = "full_name")
    private String fullName;
    @Min(value = 1900, message = "Значение должно быть больше или равно 1900")
    @Max(value = 2024, message = "Значение должно быть меньше или равно 2024")
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;
    @OneToMany(mappedBy = "owner",cascade = CascadeType.PERSIST)
    private List<Book> books;
    public Person() {
    }
    public Person(String fullName, Integer yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(fullName, person.fullName) && Objects.equals(yearOfBirth, person.yearOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, yearOfBirth);
    }
}
