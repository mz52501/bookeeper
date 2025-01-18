package com.example.javaxml_bookeeper.controller;

import com.example.javaxml_bookeeper.dto.BookDTO;
import com.example.javaxml_bookeeper.models.Book;
import com.example.javaxml_bookeeper.models.Loan;
import com.example.javaxml_bookeeper.models.User;
import com.example.javaxml_bookeeper.repository.BookRepository;
import com.example.javaxml_bookeeper.repository.LoanRepository;
import com.example.javaxml_bookeeper.repository.UserRepository;
import com.example.javaxml_bookeeper.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class LoanController {

    @Autowired
    BookService bookService;

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanController(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {this.loanRepository = loanRepository; this.bookRepository = bookRepository; this.userRepository = userRepository; }

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

    @PostMapping("/addLoan")
    public ResponseEntity<?> addLoan(@RequestBody Map<String, Object> payload) {
        try {
            Long userId = Long.valueOf(payload.get("userId").toString());
            Long bookId = Long.valueOf(payload.get("bookId").toString());
            LocalDate returnDate = LocalDate.parse(payload.get("returnDate").toString());

            // Récupération de l'utilisateur et du livre
            User user = userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new RuntimeException("User not found"));
            Book book = bookRepository.findById(Math.toIntExact(bookId)).orElseThrow(() -> new RuntimeException("Book not found"));

            // Vérification si le livre est déjà emprunté et non retourné
            Optional<Loan> existingLoan = loanRepository.findByBookAndIsReturnedFalse(book);
            if (existingLoan.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Book is already borrowed and not returned.");
            }

            // Création du prêt
            Loan loan = new Loan();
            loan.setUser(user);
            loan.setBook(book);
            loan.setLoanDate(LocalDate.now());
            loan.setReturnDate(returnDate);
            loan.setIsReturned(false); // Le prêt n'est pas encore retourné

            // Sauvegarde du prêt
            loanRepository.save(loan);
            return ResponseEntity.ok("Loan added successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }




}
