package com.example.appdevproject.Investment.Money;

public class Invest_Debt {
    private Integer id, foreinKey;
    private Double amountBorred, interestRate;
    private Integer compoundsPerYear, loanTermInMonths;


    public Invest_Debt(Double amountBorred, Double interestRate, Integer compoundsPerYear, Integer loanTermInMonths) {
        this.amountBorred = amountBorred;
        this.interestRate = interestRate;
        this.compoundsPerYear = compoundsPerYear;
        this.loanTermInMonths = loanTermInMonths;
    }
    public Invest_Debt(Integer id, Integer foreinKey, Double amountBorred, Double interestRate, Integer compoundsPerYear, Integer loanTermInMonths) {
        this.id = id;
        this.foreinKey = foreinKey;
        this.amountBorred = amountBorred;
        this.interestRate = interestRate;
        this.compoundsPerYear = compoundsPerYear;
        this.loanTermInMonths = loanTermInMonths;
    }

    //show the repayment/amortization  schedule


    //what if i pay extra $50 per month?


    //what is the total value of this loan?


    //what should my payments be to pay off the loan in 10 months?






    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForeinKey() {
        return foreinKey;
    }

    public void setForeinKey(Integer foreinKey) {
        this.foreinKey = foreinKey;
    }

    public Double getAmountBorred() {
        return amountBorred;
    }

    public void setAmountBorred(Double amountBorred) {
        this.amountBorred = amountBorred;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getCompoundsPerYear() {
        return compoundsPerYear;
    }

    public void setCompoundsPerYear(Integer compoundsPerYear) {
        this.compoundsPerYear = compoundsPerYear;
    }

    public Integer getLoanTermInMonths() {
        return loanTermInMonths;
    }

    public void setLoanTermInMonths(Integer loanTermInMonths) {
        this.loanTermInMonths = loanTermInMonths;
    }
}

