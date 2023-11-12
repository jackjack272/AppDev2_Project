package com.example.appdevproject.Investment.Models;

import com.example.appdevproject.Investment.Models.Interfaces.Debt;



public class Invest_Debt implements Debt {
    private Integer id, foreinKey;
    private String debtName;
    private Double amountBorred, interestRate;
    private Integer compoundsPerYear, loanTermInMonths;
    private Boolean isDebt;


    public Invest_Debt(String debtName, Double amountBorred, Double interestRate, Integer compoundsPerYear, Integer loanTermInMonths) {
        this.debtName = debtName;
        this.amountBorred = amountBorred;
        this.interestRate = interestRate;
        this.compoundsPerYear = compoundsPerYear;
        this.loanTermInMonths = loanTermInMonths;
    }

    public Invest_Debt(Integer id, Integer foreinKey,
                       String debtName, Double amountBorred,
                       Double interestRate, Integer compoundsPerYear,
                       Integer loanTermInMonths, int isDebt)
    {
        this.id = id;
        this.foreinKey = foreinKey;
        this.debtName = debtName;
        this.amountBorred = amountBorred;
        this.interestRate = interestRate;
        this.compoundsPerYear = compoundsPerYear;
        this.loanTermInMonths = loanTermInMonths;


        this.isDebt = isDebt==0;
    }



    public double getEffectiveInterestRate() {
        return -1.0;
    }


    public double getMarketValue(){

        return -1.0;
    }


    public double paymentPerCompound(){
        return -1.0;
    }

    public double valueAtMaturity(){
        return -1.0;
    }

    public double getAnnualCompoundRate() {
        return -1.0;
    }

    public double getMarketValue(Double currentMarketRate) {
        return -1.0;
    }












    //getters and setter
    public String getDebtName() {
        return debtName;
    }

    public void setDebtName(String debtName) {
        this.debtName = debtName;
    }

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

    public Boolean getIsDebt() {
        return isDebt;
    }

    public void setIsDebt(Boolean debt) {
        isDebt = debt;
    }
}

