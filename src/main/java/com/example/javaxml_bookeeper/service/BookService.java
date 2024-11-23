package com.example.javaxml_bookeeper.service;

import com.example.javaxml_bookeeper.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
}
