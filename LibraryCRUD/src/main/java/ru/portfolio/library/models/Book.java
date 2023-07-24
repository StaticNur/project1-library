package ru.portfolio.library.models;

import javax.persistence.*;
import javax.validation.constraints.*;
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fk_id")
    private Integer fk_id;
    @Size(min = 1, max = 200, message = "не должен быть пустым")
    @Column(name = "name")
    private String name;
    @Pattern(regexp = "([А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+)|([A-Z]\\w+ [A-Z]\\w+)", message = "Ваше полное имя должно быть в указанном формате: Иван Иванов")
    @Column(name = "author")
    private String author;
    @Min(value = 1800, message = "Значение должно быть больше или равно 1800")
    @Max(value = 2024, message = "Значение должно быть меньше или равно 2024")
    @Column(name = "year_of_publishing")
    private Integer yearOfPublishing;
    public Book() {}
    public Book(int id, Integer fk_id, String name, String author, Integer yearPublishing) {
        this.id = id;
        this.fk_id = fk_id;
        this.name = name;
        this.author = author;
        this.yearOfPublishing = yearPublishing;
    }
    public Book(int id, String name, String author, Integer yearPublishing) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfPublishing = yearPublishing;
    }
    public Integer getFk_id() {
        return fk_id;
    }

    public void setFk_id(Integer fk_id) {
        this.fk_id = fk_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(Integer yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }
}
