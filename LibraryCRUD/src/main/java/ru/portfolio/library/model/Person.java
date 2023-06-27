package ru.portfolio.library.model;

import org.springframework.validation.annotation.Validated;
import ru.portfolio.library.annotation.CustomSize;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    private int fk_id;
    @NotNull(message = "not empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Your full name should be in this format: Ivanov Ivan Ivanovich")
    private String fullName;
    @NotNull(message = "not empty")
    @CustomSize
    private Integer yearOfBirth;
    public Person() {
    }
    public Person(int id,int fk_id,String fullName, Integer yearBirth) {
        this.id = id;
        this.fk_id = fk_id;
        this.fullName = fullName;
        this.yearOfBirth = yearBirth;
    }
    public Person(int id,String fullName, Integer yearBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearBirth;
    }

    public int getFk_id() {
        return fk_id;
    }

    public void setFk_id(int fk_id) {
        this.fk_id = fk_id;
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
