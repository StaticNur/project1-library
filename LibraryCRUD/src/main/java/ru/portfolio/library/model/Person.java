package ru.portfolio.library.model;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @Size(min = 1, max = 200, message = "не должен быть пустым")
    @Pattern(regexp = "([А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+)|([A-Z]\\\\w+ [A-Z]\\\\w+ [A-Z]\\\\w+)", message = "Ваше полное имя должно быть в таком формате: Иванов Иван Иванович")
    private String fullName;
    @Min(value = 1900, message = "Значение должно быть больше или равно 1900")
    @Max(value = 2024, message = "Значение должно быть меньше или равно 2024")
    private Integer yearOfBirth;
    public Person() {
    }
    public Person(int id,String fullName, Integer yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
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
}
