package com.jsrdev.literalura.repository;

import com.jsrdev.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByNameContainsIgnoreCase(String name);

    @Query(value = "SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books b")
    List<Author> findAllAuthorsWithBooks();
}
