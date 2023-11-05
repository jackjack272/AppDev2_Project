package com.example.appdevproject.Investment.Models;

public class Sum {
    private String name;
    private Double monthlyInterest;
    private Double totalAmount;


    public Sum(String name, Double monthlyInterest, Double totalAmount) {
        this.name = name;
        this.monthlyInterest = monthlyInterest;
        this.totalAmount = totalAmount;
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
