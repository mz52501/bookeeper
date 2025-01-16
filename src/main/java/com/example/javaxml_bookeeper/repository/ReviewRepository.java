package com.example.javaxml_bookeeper.repository;

import com.example.javaxml_bookeeper.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findByBookIdAndUserId(Integer bookId, Integer userId);
}
