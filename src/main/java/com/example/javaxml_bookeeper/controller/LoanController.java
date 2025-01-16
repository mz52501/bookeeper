package com.example.javaxml_bookeeper.controller;

import com.example.javaxml_bookeeper.dto.BookDTO;
import com.example.javaxml_bookeeper.models.Book;
import com.example.javaxml_bookeeper.repository.BookRepository;
import com.example.javaxml_bookeeper.repository.LoanRepository;
import com.example.javaxml_bookeeper.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {

    @Autowired
    BookService bookService;

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    public LoanController(LoanRepository loanRepository, BookRepository bookRepository) {this.loanRepository = loanRepository; this.bookRepository = bookRepository; }

    @GetMapping("/returnedBooks/{userId}")
    public ResponseEntity<List<Book>> usersReturnedBooks(@PathVariable Integer userId) {
        List<Book> returnedBooks = bookService.getReturnedBooks(userId);
        if(returnedBooks.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(returnedBooks);
    }

    @GetMapping("/loanedBooks/{userId}")
    public ResponseEntity<List<Book>> usersLoanedBooks(@PathVariable Integer userId) {
        List<Book> loanedBooks = bookService.getLoanedBooks(userId);
        if(loanedBooks.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(loanedBooks);
    }
}
