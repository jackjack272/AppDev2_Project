package com.example.appdevproject.Investment.Models;

public class Invest_Bonds extends Invest_Debt{


    public Invest_Bonds(String debtName, Double amountBorred, Double interestRate, Integer compoundsPerYear, Integer loanTermInMonths) {
        super(debtName, amountBorred, interestRate, compoundsPerYear, loanTermInMonths);
    }

    public Invest_Bonds(Integer id, Integer foreinKey, String debtName, Double amountBorred, Double interestRate, Integer compoundsPerYear, Integer loanTermInMonths) {
        super(id, foreinKey, debtName, amountBorred, interestRate, compoundsPerYear, loanTermInMonths);
    }
}

