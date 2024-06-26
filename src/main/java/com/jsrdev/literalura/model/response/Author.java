package com.jsrdev.literalura.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Author(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") int birthYear,
        @JsonAlias("death_year") int deathYear
) { }
