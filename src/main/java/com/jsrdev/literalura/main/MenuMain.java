package com.jsrdev.literalura.main;

import com.jsrdev.literalura.model.response.ResponseData;
import com.jsrdev.literalura.service.ApiService;
import com.jsrdev.literalura.service.ConvertData;
import com.jsrdev.literalura.utils.Constants;

public class MenuMain {
    private final ApiService service = new ApiService();
    private final ConvertData convertData = new ConvertData();

    String baseUrl = Constants.BASE_URL;

    public void showMenu() {
        String url = baseUrl + "books/";
        var json = service.getData(url);
        System.out.println();
        System.out.println("Response => " + json);

        var books = convertData.getData(json, ResponseData.class);

        System.out.println();
        books.results().forEach(System.out::println);
    }
}