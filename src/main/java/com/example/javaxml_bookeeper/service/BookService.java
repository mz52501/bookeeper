package com.example.javaxml_bookeeper.service;

import com.example.javaxml_bookeeper.dto.BookDTO;
import com.example.javaxml_bookeeper.dto.LoanBookDTO;
import com.example.javaxml_bookeeper.dto.ReviewDTO;
import com.example.javaxml_bookeeper.dto.UserLoanBookDTO;
import com.example.javaxml_bookeeper.models.Book;
import com.example.javaxml_bookeeper.models.Loan;
import com.example.javaxml_bookeeper.models.Review;
import com.example.javaxml_bookeeper.models.User;
import com.example.javaxml_bookeeper.repository.BookRepository;
import com.example.javaxml_bookeeper.repository.LoanRepository;
import com.example.javaxml_bookeeper.repository.ReviewRepository;
import com.example.javaxml_bookeeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Book> getReturnedBooks(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        List<Book> books = new ArrayList<>();
        if(user != null) {
            for (Loan loan : user.getLoans()) {
                if (loan.getIsReturned() == true) {
                    books.add(loan.getBook());
                }
            }
        }
        return books;
    }

    public List<Book> getLoanedBooks(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        List<Book> books = new ArrayList<>();
        if(user != null) {
            for (Loan loan : user.getLoans()) {
                if (loan.getIsReturned() == false) {
                    books.add(loan.getBook());
                }
            }
        }
        return books;
    }

    public List<UserLoanBookDTO> getUsersLoans() {
        List<User> users = userRepository.findAll();
        List<UserLoanBookDTO> userLoans = new ArrayList<>();
        for(User user : users) {
            List<LoanBookDTO> loansBooks = new ArrayList<>();
            if(!user.getLoans().isEmpty()) {
                for (Loan loan : user.getLoans()) {
                    if(!loan.getIsReturned()) {
                        LoanBookDTO dto = new LoanBookDTO(loan.getId(), loan.getLoanDate(), loan.getBook().getId(), loan.getBook().getTitle(), loan.getBook().getAuthor());
                        loansBooks.add(dto);
                    }
                }
                UserLoanBookDTO dto = new UserLoanBookDTO(user.getId(), user.getName(), user.getSurname(), user.getDob(), user.getLogin(), loansBooks);
                userLoans.add(dto);
            }
        }
        return userLoans;
    }

    public void leaveReviewComment(ReviewDTO reviewDTO) {
        // Attempt to find an existing review for the given book and user
        Optional<Review> existingReview = reviewRepository.findByBookIdAndUserId(
            reviewDTO.getBookId(),
            reviewDTO.getUserId()
        );

        if (existingReview.isPresent()) {
            // Update the existing review
            Review review = existingReview.get();
            review.setRating(reviewDTO.getRating());
            review.setComment(reviewDTO.getComment());
            review.setCreated(LocalDateTime.ofInstant(reviewDTO.getCreated(), ZoneOffset.UTC));
            reviewRepository.save(review);
        } else {
            // Create a new review
            Review review = new Review();
            review.setBook(bookRepository.findById(reviewDTO.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found")));
            review.setUser(userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found")));
            review.setRating(reviewDTO.getRating());
            review.setComment(reviewDTO.getComment());
            review.setCreated(LocalDateTime.ofInstant(reviewDTO.getCreated(), ZoneOffset.UTC));
            reviewRepository.save(review);
        }
    }
}
