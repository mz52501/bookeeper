package com.example.javaxml_bookeeper.dto;

import java.time.LocalDate;
import java.util.List;

public class UserLoanBookDTO {
    private Integer userId;
    private String name;
    private String surname;
    private LocalDate dob;
    private String login;
    private List<LoanBookDTO> loans;

    public UserLoanBookDTO(Integer userId, String name, String surname, LocalDate dob, String login, List<LoanBookDTO> loans) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.login = login;
        this.loans = loans;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<LoanBookDTO> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanBookDTO> loans) {
        this.loans = loans;
    }
}
