package com.jsrdev.literalura.main;

import com.jsrdev.literalura.model.Book;
import com.jsrdev.literalura.model.response.AuthorData;
import com.jsrdev.literalura.model.response.BookData;
import com.jsrdev.literalura.model.response.ResponseData;
import com.jsrdev.literalura.service.ApiService;
import com.jsrdev.literalura.service.ConvertData;
import com.jsrdev.literalura.utils.Constants;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Response {

    private final ApiService service = new ApiService();
    private final ConvertData convertData = new ConvertData();

    String baseUrl = Constants.BASE_URL;

    private final Scanner scanner = new Scanner(System.in);

    List<Book> bookList;

    public void getBooksFromAPI() {
        String url = baseUrl + "books/";
        var json = service.getData(url);

        var data = convertData.getData(json, ResponseData.class);

        // Convert ResponseData to a Book
        bookList = getConvertResponseDataToABook(data);

        //System.out.println();
        //bookList.forEach(System.out::println);

        //mostrar solo titulos de los libros
        System.out.println();
        System.out.println("------------ Titulos de Libros encontrados -------------");
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(i+1 + ".- Title: " + bookList.get(i).getTitle());
        }
        //bookList.forEach(b -> System.out.println("Book => " + b.getTitle()));
    }

    public void getBookByTitleFromAPI() {
        //Crime and Punishment, Dracula, Don Quijote
        System.out.println("Ingresa el nombre del Libro que deseas ver:");
        var bookName = scanner.nextLine().trim();

        String url = baseUrl + "books/?search=" + encodeAndFormatBookName(bookName);
        var json = service.getData(url);

        var data = convertData.getData(json, ResponseData.class);

        System.out.println();
        System.out.println("------------ BOOK ----------------");
        data.results().forEach(b -> {
            System.out.println("Title: " + b.title());
            b.authors().forEach(a -> System.out.println("Author: " + a.name()));
            System.out.println("Language: " + b.languages());
            System.out.println("Download count: " + b.downloadCount());
            System.out.println("********************************");
        });

        /*Optional<Book> bookByTitle = bookList.stream()
                .filter(b -> b.getTitle().toUpperCase().contains(bookName.toUpperCase()))
                .findFirst();

        if (bookByTitle.isPresent()) {
            var book = bookByTitle.get();
            System.out.println("Title => " + book.getTitle());
        } else System.out.println("No encontre el libro: " + bookName + ", intenta de nuevo!"); */

    }

    public void getBooksFromDB() {

    }

    public void getAuthors() {

    }

    public void getBirthYearAuthorsByYear() {

    }

    public void getBooksByLanguage() {
        var language = "es";
        String url = baseUrl + "books/?languages=" + language;

        var json = service.getData(url);
        var data = convertData.getData(json, ResponseData.class);

        // Convert ResponseData to a Book
        bookList = getConvertResponseDataToABook(data);

        System.out.println();
        System.out.println("-------- Libros encontrados del Idioma - " + language.toUpperCase() +"  ------------");
        bookList.forEach(b -> System.out.println("Title: => " + b.getTitle()));
    }

    private String encodeAndFormatBookName(String bookName) {
        String encodedBookName = URLEncoder.encode(bookName, StandardCharsets.UTF_8);
        return encodedBookName.replace("+", "%20");
    }

    private List<Book> getConvertResponseDataToABook(ResponseData data) {
        return data.results().stream()
                .map(b -> new Book( new BookData(
                        b.id(),
                        b.title(),
                        b.authors().stream()
                                .map(a -> new AuthorData(
                                        a.name(),
                                        a.birthYear(),
                                        a.deathYear()
                                ))
                                .collect(Collectors.toList()),
                        b.subjects(),
                        b.bookshelves(),
                        b.languages(),
                        b.copyright(),
                        b.mediaType(),
                        b.formats(),
                        b.downloadCount()
                )))
                .collect(Collectors.toList());
    }
}
