package com.example.appdevproject.Investment.Models;

public class Totals_Save {

    /**
     * This is a object of the table .
     *
     *
     */
    private Integer id;
    private Integer foreignKey;

    private String name; //bond

    private Double totalAmount; //  $3000

    private Double yearlyInterestCharge; //10.4%


    private Double yearlyGain; // 3000*


    public Totals_Save(String name, Double yearlyGain, Double totalAmount, Double yearlyInterestCharge) {
        this.name = name;
        this.yearlyGain = yearlyGain;
        this.totalAmount = totalAmount;
        this.yearlyInterestCharge = yearlyInterestCharge;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(Integer foreignKey) {
        this.foreignKey = foreignKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getYearlyGain() {
        return yearlyGain;
    }

    public void setYearlyGain(Double yearlyGain) {
        this.yearlyGain = yearlyGain;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getYearlyInterestCharge() {
        return yearlyInterestCharge;
    }

    public void setYearlyInterestCharge(Double yearlyInterestCharge) {
        this.yearlyInterestCharge = yearlyInterestCharge;
    }
}
