package ru.portfolio.library.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 1, max = 200, message = "не должен быть пустым")
    @Column(name = "name")
    private String name;
    @Column(name = "fk_id")
    private Integer fkId;
    @Pattern(regexp = "([А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+)|([A-Z]\\w+ [A-Z]\\w+)", message = "Ваше полное имя должно быть в указанном формате: Иван Иванов")
    @Column(name = "author")
    private String author;
    @Min(value = 1800, message = "Значение должно быть больше или равно 1800")
    @Max(value = 2024, message = "Значение должно быть меньше или равно 2024")
    @Column(name = "year_of_publishing")
    private Integer yearOfPublishing;
    @ManyToOne
    @JoinColumn(name = "fk_id",referencedColumnName = "id", insertable = false, updatable = false)
    private Person owner;
    public Book() {}
    public Book(String name, String author, Integer yearPublishing) {
        this.name = name;
        this.author = author;
        this.yearOfPublishing = yearPublishing;
    }

    public Integer getFkId() {
        return fkId;
    }

    public void setFkId(Integer fkId) {
        this.fkId = fkId;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(name, book.name) && Objects.equals(author, book.author) && Objects.equals(yearOfPublishing, book.yearOfPublishing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, yearOfPublishing);
    }
}
