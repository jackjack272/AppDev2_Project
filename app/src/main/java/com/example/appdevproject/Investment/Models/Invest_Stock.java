package com.example.appdevproject.Investment.Models;

public class Invest_Stock {
    /**
     * take the average of the last 6 months worth of data
     */

    private int id, foreignKey;

    private String ticker;
    private double price, dividends;
    private double lastSixMonthClose, lastSixMonthOpen;
    private int quantity;
    private double yearlyEarn;


    public Invest_Stock(String ticker, double price, double dividends, double lastSixMonthClose, double lastSixMonthOpen, int quantity) {
        this.ticker = ticker;
        this.price = price;
        this.dividends = dividends;
        this.lastSixMonthClose = lastSixMonthClose;
        this.lastSixMonthOpen = lastSixMonthOpen;
        this.quantity = quantity;
        this.yearlyEarn=this.quantity* this.dividends*12;
    }

    public Invest_Stock(int id, int foreignKey, String ticker, double price, double dividends, double lastSixMonthClose,
                        double lastSixMonthOpen, int quantity) {
        this.id = id;
        this.foreignKey = foreignKey;
        this.ticker = ticker;
        this.price = price;
        this.dividends = dividends;

        this.lastSixMonthClose = lastSixMonthClose;
        this.lastSixMonthOpen = lastSixMonthOpen;
        this.quantity = quantity;
        this.yearlyEarn=this.quantity* this.dividends*12;
    }


    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getYearlyEarn() {
        return yearlyEarn;
    }

    public void setYearlyEarn(double yearlyEarn) {
        this.yearlyEarn = yearlyEarn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(int foreignKey) {
        this.foreignKey = foreignKey;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDividends() {
        return dividends;
    }

    public void setDividends(double dividends) {
        this.dividends = dividends;
    }

    public double getLastSixMonthClose() {
        return lastSixMonthClose;
    }

    public void setLastSixMonthClose(double lastSixMonthClose) {
        this.lastSixMonthClose = lastSixMonthClose;
    }

    public double getLastSixMonthOpen() {
        return lastSixMonthOpen;
    }

    public void setLastSixMonthOpen(double lastSixMonthOpen) {
        this.lastSixMonthOpen = lastSixMonthOpen;
    }
}
