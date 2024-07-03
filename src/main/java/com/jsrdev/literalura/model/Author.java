package com.jsrdev.literalura.model;

import com.jsrdev.literalura.model.response.AuthorData;

public class Author {
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    public Author(AuthorData author) {
        this.name = author.name();
        this.birthYear = author.birthYear();
        this.deathYear = author.deathYear();
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear;
    }
}
