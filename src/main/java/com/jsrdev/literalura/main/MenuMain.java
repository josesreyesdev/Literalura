package com.jsrdev.literalura.main;

import com.jsrdev.literalura.repository.AuthorRepository;
import com.jsrdev.literalura.repository.BookRepository;

import java.util.Scanner;

public class MenuMain {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final Scanner scanner = new Scanner(System.in);

    public MenuMain(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        Response response = new Response(bookRepository, authorRepository);

        var option = -1;
        while (option != 0) {
            var menu = """
                     Elija la opción que desee realizar, a través del número:
                     1.- Consultar Libros desde la API
                     2.- Buscar Libro por Título desde la API.
                     3.- Listar Libros registrados en la BD.
                     4.- Listar Autores registrados en la BD.
                     5.- Listar Autores vivos en un determinado año de la BD.
                     6.- Listar Libros por Idioma de la BD.
                    \s
                     0. Salir;
                    \s""";

            System.out.println();
            System.out.println(menu);

            // Verifica si hay un entero disponible para leer
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Introduce un número entero válido:");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }

            option = scanner.nextInt();
            scanner.nextLine();

            System.out.println();
            switch (option) {
                case 1:
                    response.getBooksFromAPI();
                    break;
                case 2:
                    response.getBookByTitleFromAPI();
                    break;
                case 3:
                    response.getBooksFromDB();
                    break;
                case 4:
                    response.getAuthors();
                    break;
                case 5:
                    response.getBirthYearAuthorsByYear();
                    break;
                case 6:
                    response.getBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Closed application");
                    break;
                default:
                    System.out.println("Invalid Option, Try Again!");
            }
        }

    }

}