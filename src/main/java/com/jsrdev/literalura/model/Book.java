package com.jsrdev.literalura.model;

import com.jsrdev.literalura.model.response.AuthorData;
import com.jsrdev.literalura.model.response.BookData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Book {
    private Long id;
    private String title;
    private List<Author> authors;
    private List<String> subjects;
    private List<String> bookshelves;
    private List<String> languages;
    private Boolean copyright;
    private String mediaType;
    private Map<String, String> formats;
    private Integer downloadCount;

    public Book(BookData bookData) {
        this.id = bookData.id();
        this.title = bookData.title();
        this.authors = convertBookDataToABook(bookData.authors());
        this.subjects = bookData.subjects();
        this.bookshelves = bookData.bookshelves();
        this.languages = bookData.languages();
        this.copyright = bookData.copyright();
        this.mediaType = bookData.mediaType();
        this.formats = bookData.formats();
        this.downloadCount = bookData.downloadCount();
        ;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title='" + title + '\'' + ", authors=" + authors + ", subjects=" + subjects + ", bookshelves=" + bookshelves + ", languages=" + languages + ", copyright=" + copyright + ", mediaType='" + mediaType + '\'' + ", formats=" + formats + ", downloadCount=" + downloadCount + '}';
    }

    private List<Author> convertBookDataToABook(List<AuthorData> authors) {
        return authors.stream()
                .map(a -> new Author(new AuthorData(
                        a.name(),
                        a.birthYear(),
                        a.deathYear()
                )))
                .collect(Collectors.toList());
    }
}
