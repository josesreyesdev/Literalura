package com.jsrdev.literalura.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("id") Long id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<AuthorData> authors,
        @JsonAlias("subjects") List<String> subjects,
        @JsonAlias("bookshelves") List<String> bookshelves,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("copyright") boolean copyright,
        @JsonAlias("media_type") String mediaType,
        @JsonAlias("download_count") int downloadCount
) {
}
