package com.jsrdev.literalura;

import com.jsrdev.literalura.main.MenuMain;
import com.jsrdev.literalura.repository.AuthorRepository;
import com.jsrdev.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		MenuMain menu = new MenuMain(bookRepository, authorRepository);
		menu.showMenu();
	}
}
