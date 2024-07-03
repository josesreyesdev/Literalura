package com.jsrdev.literalura.model;

import com.jsrdev.literalura.model.response.AuthorData;
import jakarta.persistence.Embeddable;

@Embeddable
public class Author {
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    public Author(AuthorData author) {
        this.name = author.name();
        this.birthYear = author.birthYear();
        this.deathYear = author.deathYear();
    }

    public Author() {
    }

    public String getName() {
        return name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear;
    }
}
