package com.jsrdev.literalura.main;

import com.jsrdev.literalura.model.response.ResponseData;
import com.jsrdev.literalura.service.ApiService;
import com.jsrdev.literalura.service.ConvertData;
import com.jsrdev.literalura.utils.Constants;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Response {

    private final ApiService service = new ApiService();
    private final ConvertData convertData = new ConvertData();

    String baseUrl = Constants.BASE_URL;

    private final Scanner scanner = new Scanner(System.in);

    public void fetchResponseData() {
        String url = baseUrl + "books/";
        var json = service.getData(url);

        var data = convertData.getData(json, ResponseData.class);

        System.out.println();
        System.out.println(data);
        data.results().forEach(System.out::println);
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
