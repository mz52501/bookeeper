package com.example.javaxml_bookeeper.dto;

import java.time.LocalDate;

public class LoanBookDTO {
    private Integer loanId;
    private LocalDate loanDate;
    private Integer bookId;
    private String title;
    private String author;

    public LoanBookDTO(Integer loanId, LocalDate loanDate, Integer bookId, String title, String author) {
        this.loanId = loanId;
        this.loanDate = loanDate;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
