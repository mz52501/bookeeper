package com.example.javaxml_bookeeper.controller;

import com.example.javaxml_bookeeper.models.Book;
import com.example.javaxml_bookeeper.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookRepository.findAll(); // Fetch all books
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no books found
        } else {
            return ResponseEntity.ok(books); // Return 200 with the list of books
        }
    }

}
