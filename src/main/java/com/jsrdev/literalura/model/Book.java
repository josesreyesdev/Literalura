package com.jsrdev.literalura.model;

import com.jsrdev.literalura.model.response.AuthorData;
import com.jsrdev.literalura.model.response.BookData;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Book")
@Table(name = "books")
public class Book {
    @Id
    @Column(unique = true)
    private Long id;
    private String title;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"))
    private List<Author> authors;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_subjects", joinColumns = @JoinColumn(name = "book_id"))
    private List<String> subjects;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_bookshelves", joinColumns = @JoinColumn(name = "book_id"))
    private List<String> bookshelves;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_languages", joinColumns = @JoinColumn(name = "book_id"))
    private List<String> languages;
    private Boolean copyright;
    @Column(name = "media_type")
    private String mediaType;
    private Integer downloadCount;

    public Book(BookData bookData) {
        this.id = bookData.id();
        this.title = bookData.title();
        this.authors = convertBookDataToAuthors(bookData.authors());
        this.subjects = bookData.subjects();
        this.bookshelves = bookData.bookshelves();
        this.languages = bookData.languages();
        this.copyright = bookData.copyright();
        this.mediaType = bookData.mediaType();
        this.downloadCount = bookData.downloadCount();
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", subjects=" + subjects +
                ", bookshelves=" + bookshelves +
                ", languages=" + languages +
                ", copyright=" + copyright +
                ", mediaType='" + mediaType +
                '\'' + ", downloadCount=" + downloadCount +
                '}';
    }

    private List<Author> convertBookDataToAuthors(List<AuthorData> authorDataList) {
        return authorDataList.stream()
                .map(Author::new)
                .collect(Collectors.toList());
    }
}
