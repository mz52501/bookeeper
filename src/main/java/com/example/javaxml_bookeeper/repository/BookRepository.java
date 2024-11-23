package com.example.javaxml_bookeeper.repository;

import com.example.javaxml_bookeeper.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
