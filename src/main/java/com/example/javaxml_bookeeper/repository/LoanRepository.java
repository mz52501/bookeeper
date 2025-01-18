package com.example.javaxml_bookeeper.repository;

import com.example.javaxml_bookeeper.models.Book;
import com.example.javaxml_bookeeper.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    Optional<Loan> findByBookAndReturnDateAfter(Book book, LocalDate date);
    Optional<Loan> findByBookAndIsReturnedFalse(Book book);
}
