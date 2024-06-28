package com.jsrdev.literalura.model;

import java.util.List;
import java.util.Map;

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

    public Book(
            Long id,
            String title,
            List<Author> authors,
            List<String> subjects,
            List<String> bookshelves,
            List<String> languages,
            boolean copyright,
            String mediaType,
            Map<String, String> formats,
            int downloadCount) {

        this.id = id;
        this.title = title;
        this.authors = authors;
        this.subjects = subjects;
        this.bookshelves = bookshelves;
        this.languages = languages;
        this.copyright = copyright;
        this.mediaType = mediaType;
        this.formats = formats;
        this.downloadCount = downloadCount;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", subjects=" + subjects +
                ", bookshelves=" + bookshelves +
                ", languages=" + languages +
                ", copyright=" + copyright +
                ", mediaType='" + mediaType + '\'' +
                ", formats=" + formats +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
