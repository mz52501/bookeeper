package com.example.javaxml_bookeeper.controller;

import com.example.javaxml_bookeeper.models.Book;
import com.example.javaxml_bookeeper.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            // Validate the book (basic example)
            if (book.getTitle() == null || book.getTitle().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Book title is required.");
            }

            // Save the book
            Book savedBook = bookRepository.save(book);

            // Return ResponseEntity with CREATED status
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBook);

        } catch (Exception e) {
            // Handle exceptions and return appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred while saving the book: " + e.getMessage());
        }
    }

}
