package ru.portfolio.library.model;

import ru.portfolio.library.annotation.CustomSize;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Book {
    int id;
    @NotEmpty(message = "not empty")
    private String name;
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Your full name should be in this format: Ivan Ivanov")
    private String author;
    @NotEmpty(message = "not empty")
    @CustomSize(min = 1000)
    private Integer yearOfPublishing;
    public Book() {}
    public Book(int id, String name, String author, Integer yearPublishing) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfPublishing = yearPublishing;
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
