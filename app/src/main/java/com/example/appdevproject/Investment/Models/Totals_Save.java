package com.example.appdevproject.Investment.Models;

public class Totals_Save {
    private String name;
    private Double monthlyInterest;
    private Double totalAmount;
    private Double amountChanged;


    public Totals_Save(String name, Double monthlyInterest, Double totalAmount, Double amountChanged) {
        this.name = name;
        this.monthlyInterest = monthlyInterest;
        this.totalAmount = totalAmount;
        this.amountChanged =amountChanged;
    }

    public Double getAmountChanged() {
        return amountChanged;
    }

    public void setAmountChanged(Double amountChanged) {
        this.amountChanged = amountChanged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMonthlyInterest() {
        return monthlyInterest;
    }

    public void setMonthlyInterest(Double monthlyInterest) {
        this.monthlyInterest = monthlyInterest;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }






}
