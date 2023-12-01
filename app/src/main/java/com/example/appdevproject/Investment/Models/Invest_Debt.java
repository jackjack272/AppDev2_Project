package com.example.appdevproject.Investment.Models;

import com.example.appdevproject.Debt_Repayment.Models.RepaySchedualeItem;
import com.example.appdevproject.Investment.Models.Interfaces.Debt;

import java.util.List;


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

    public Invest_Debt(Integer id, Integer foreinKey,String debtName, Double amountBorred,Double interestRate, Integer compoundsPerYear,Integer loanTermInMonths, int isDebt){
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
        // Calculate the effective interest rate using the formula
        // Effective Interest Rate = (1 + (Nominal Rate / Compounds per year)) ^ Compounds per year - 1

        // Convert the annual interest rate to a decimal and calculate periodic interest rate
        double nominalRate = interestRate / 100;
        double periodicInterestRate = nominalRate / compoundsPerYear;

        // Calculate effective annual interest rate
        double effectiveInterestRate = Math.pow(1 + periodicInterestRate, compoundsPerYear) - 1;

        // Return the effective interest rate
        return effectiveInterestRate * 100; // Convert back to percentage
    }
    public double paymentPerCompound(){
        return amountBorred*getEffectiveInterestRate()/100/getCompoundsPerYear();
    }
    public double valueAtMaturity(){
        double r = interestRate / 100; // converting interest rate to a decimal
        double n = compoundsPerYear;
        double t = (double) loanTermInMonths / 12; // converting loan term to years

        double maturityValue;

        if (isDebt) {
            maturityValue = this.amountBorred * Math.pow(1 + r / n, n * t);
        } else {
            maturityValue = this.amountBorred * (1 + r * t);
        }

        return maturityValue;
    }



    public double getMonthlyPatment(){
        return getAmountBorred()*getEffectiveInterestRate()/12;
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

