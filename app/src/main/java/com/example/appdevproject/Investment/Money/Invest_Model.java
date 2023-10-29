package com.example.appdevproject.Investment.Money;

public class Invest_Model {

    private Integer Id;

    // i borrowed $2000 at 5.25% compounded semi annually.
    // repayment over 15 months.

    // what is the total interest paid?
    // what is the total cost of this loan?
    // what if i pay extra $25 monthly?

    private Double amount;
    private Double interestRate;
    private Integer compoundsPerYear;
    private Double months;
    private Integer foreighnKey;


    public Invest_Model(Double amount, Double interestRate, Integer compoundsPerYear, Double months) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.compoundsPerYear = compoundsPerYear;
        this.months = months;
    }

    public Invest_Model(Integer id, Double amount, Double interestRate, Integer compoundsPerYear, Double months, Integer foreighnKey) {
        Id = id;
        this.amount = amount;
        this.interestRate = interestRate;
        this.compoundsPerYear = compoundsPerYear;
        this.months = months;
        this.foreighnKey = foreighnKey;
    }


//getters


    public Integer getId() {
        return Id;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Integer getCompoundsPerYear() {
        return compoundsPerYear;
    }

    public Double getMonths() {
        return months;
    }

    public Integer getForeighnKey() {
        return foreighnKey;
    }
}