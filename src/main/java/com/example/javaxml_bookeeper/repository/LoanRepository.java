package com.example.javaxml_bookeeper.repository;

import com.example.javaxml_bookeeper.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
}
