package com.example.javaxml_bookeeper.controller;

import com.example.javaxml_bookeeper.dto.ReviewDTO;
import com.example.javaxml_bookeeper.models.Book;
import com.example.javaxml_bookeeper.repository.BookRepository;
import com.example.javaxml_bookeeper.repository.ReviewRepository;
import com.example.javaxml_bookeeper.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    public BookController(BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/books")
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

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/review")
    public ResponseEntity<?> leaveReview(@RequestBody ReviewDTO reviewDTO) {
        try {
            bookService.leaveReviewComment(reviewDTO); // Handle both tasks in the service
            return ResponseEntity.ok("Review updated");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error leaving review: " + e.getMessage());
        }
    }



}
