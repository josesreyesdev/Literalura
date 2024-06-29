package com.jsrdev.literalura.main;

import java.util.Scanner;

public class MenuMain {

    private final Scanner scanner = new Scanner(System.in);
    Response response = new Response();

    public void showMenu() {

        var option = -1;
        while (option != 0) {
            var menu = """
                     Elija la opción que desee realizar, a través del número:
                     1.- Buscar Libro por Título.
                     2.- Listar Libros registrados.
                     3.- Listar Autores registrados.
                     4.- Listar Autores vivos en un determinado año.
                     5.- Listar Libros por Idioma.
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
                    response.fetchResponseData();
                    break;
                case 2:
                    response.getBookByTitle();
                    break;
                case 3:
                    response.getAuthors();
                    break;
                case 4:
                    response.getBirthYearAuthorsByYear();
                    break;
                case 5:
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