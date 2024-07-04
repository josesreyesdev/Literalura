package com.jsrdev.literalura.main;

import com.jsrdev.literalura.repository.BookRepository;
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

    private final BookRepository repository;

    public Response(BookRepository repository) {
        this.repository = repository;
    }

    public void getBooksFromAPI() {
        String url = baseUrl + "books/";
        var json = service.getData(url);

        var data = convertData.getData(json, ResponseData.class);

        // Convert ResponseData to a Book
        bookList = ConvertResponseDataToBooks(data);

        //System.out.println();
        //bookList.forEach(System.out::println);

        //mostrar solo titulos de los libros
        System.out.println();
        System.out.println("------------ Titulos de Libros encontrados -------------");
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(i + 1 + ".- Title: " + bookList.get(i).getTitle());
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
        if (!data.results().isEmpty()) {

            // Convert ResponseData to a Book
            bookList = ConvertResponseDataToBooks(data);

            bookList.forEach(book -> {

                if (repository.findBookById(book.getId()).isEmpty()) {
                    //Save in Db
                    repository.save(book);
                } else System.out.println("El libro ya se encuentra la BD");

                showBooks(book);
            });
        } else System.out.println("No encontre Libros con este Título: " + bookName);

        /*Optional<Book> bookByTitle = bookList.stream()
                .filter(b -> b.getTitle().toUpperCase().contains(bookName.toUpperCase()))
                .findFirst();

        if (bookByTitle.isPresent()) {
            var book = bookByTitle.get();
            System.out.println("Title => " + book.getTitle());
        } else System.out.println("No encontre el libro: " + bookName + ", intenta de nuevo!"); */

    }

    public void getBooksFromDB() {
        bookList = repository.findAll();

        if (!bookList.isEmpty()) {
            bookList.stream().forEach(b -> showBooks(b));
        } else System.out.println("No encontre libros registrados en la BD");
    }

    public void getAuthors() {
        /*
        * Muestra:
        * Author: Names
        * Fecha de Nacimiento: fecha
        * Fecha de Fallecimiento: Fecha
        * Libros: List[libros]
        *  */
    }

    public void getBirthYearAuthorsByYear() {
        /*
        * Ingrese el año vivo de autor(es) que desee buscar:
        * 1600
        *
        * Muestra:
        * Author: Names
        * Fecha de Nacimiento: fecha
        * Fecha de Fallecimiento: Fecha
        * Libros: List[libros]
        * */
    }

    public void getBooksByLanguage() {

        /*
         * Ingrese el idioma para buscar los libros:
         * es - español
         * en - inglés
         * fr - francés
         * pt - portugués
         *
         * Muestra:
         * Author: Names
         * Fecha de Nacimiento: fecha
         * Fecha de Fallecimiento: Fecha
         * Libros: List[libros]
         * */



        /*var language = "es";
        String url = baseUrl + "books/?languages=" + language;

        var json = service.getData(url);
        var data = convertData.getData(json, ResponseData.class);

        // Convert ResponseData to a Book
        bookList = ConvertResponseDataToBooks(data);

        System.out.println();
        System.out.println("-------- Libros encontrados del Idioma - " + language.toUpperCase() + "  ------------");
        bookList.forEach(b -> System.out.println("Title: => " + b.getTitle())); */
    }

    private String encodeAndFormatBookName(String bookName) {
        String encodedBookName = URLEncoder.encode(bookName, StandardCharsets.UTF_8);
        return encodedBookName.replace("+", "%20");
    }

    private List<Book> ConvertResponseDataToBooks(ResponseData data) {
        return data.results().stream()
                .map(b -> new Book(new BookData(
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
                        b.downloadCount()
                )))
                .collect(Collectors.toList());
    }

    private void showBooks(Book book) {
        System.out.println();
        System.out.println("************************** LIBRO **************************");
        System.out.println("Title: " + book.getTitle());
        book.getAuthors().forEach(a -> System.out.println("Author: " + a.getName()));
        System.out.println("Language: " + book.getLanguages());
        System.out.println("Download count: " + book.getDownloadCount());
        System.out.println("***********************************************************");
    }
}
