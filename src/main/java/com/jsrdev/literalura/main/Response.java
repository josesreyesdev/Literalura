package com.jsrdev.literalura.main;

import com.jsrdev.literalura.model.Author;
import com.jsrdev.literalura.model.Book;
import com.jsrdev.literalura.model.response.ResponseData;
import com.jsrdev.literalura.service.ApiService;
import com.jsrdev.literalura.service.ConvertData;
import com.jsrdev.literalura.utils.Constants;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Response {

    private final ApiService service = new ApiService();
    private final ConvertData convertData = new ConvertData();

    String baseUrl = Constants.BASE_URL;

    private final Scanner scanner = new Scanner(System.in);

    List<Book> bookList;

    public void fetchResponseData() {
        String url = baseUrl + "books/";
        var json = service.getData(url);

        var data = convertData.getData(json, ResponseData.class);

        // Convert ResponseData to a Book
        /*bookList = data.results().stream().
                map(b -> new Book(
                        b.id(), b.title(), b.authors().stream()
                        .map(a -> new Author(
                                a.name(),
                                a.birthYear(),
                                a.deathYear()
                        )).collect(Collectors.toList()),
                        b.subjects(), b.bookshelves(), b.languages(),
                        b.copyright(), b.mediaType(), b.formats(), b.downloadCount()
                ))
                .collect(Collectors.toList()); */
        // Ejemplo con flatMap en autores si fuera necesario
        bookList = data.results().stream()
                .flatMap(b -> b.authors().stream()
                        .map(a -> new Book(
                                b.id(), b.title(), Collections.singletonList(new Author(
                                a.name(),
                                a.birthYear(),
                                a.deathYear()
                        )),
                                b.subjects(), b.bookshelves(), b.languages(),
                                b.copyright(), b.mediaType(), b.formats(), b.downloadCount()
                        ))
                )
                .collect(Collectors.toList());


        System.out.println();
        bookList.forEach(System.out::println);

        //mostrar solo titulos de los libros
        System.out.println();
        System.out.println("------------ Titulos de Libros encontrados -------------");
        bookList.forEach(b -> System.out.println("Book => " + b.getTitle()));
    }

    public void getBookByTitle() {
        //Crime and Punishment, Dracula, Don Quijote

        System.out.println("Ingresa el nombre del Libro que deseas ver:");
        var bookName = scanner.nextLine().trim();

        String url = baseUrl + "books/?search=" + encodeAndFormatBookName(bookName);
        var json = service.getData(url);

        var data = convertData.getData(json, ResponseData.class);

        System.out.println();
        data.results().forEach(b -> System.out.println("Title book: " + b.title()));

        /*Optional<Book> bookByTitle = bookList.stream()
                .filter(b -> b.getTitle().toUpperCase().contains(bookName.toUpperCase()))
                .findFirst();

        if (bookByTitle.isPresent()) {
            var book = bookByTitle.get();
            System.out.println("Title => " + book.getTitle());
        } else System.out.println("No encontre el libro: " + bookName + ", intenta de nuevo!"); */

    }

    public void getAuthors() {

    }

    public void getAuthorsBirthYearByYear() {

    }

    public void getBooksByIdiom() {

    }

    private String encodeAndFormatBookName(String bookName) {
        String encodedBookName = URLEncoder.encode(bookName, StandardCharsets.UTF_8);
        return encodedBookName.replace("+", "%20");
    }
}
