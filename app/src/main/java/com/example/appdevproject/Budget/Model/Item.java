package com.example.appdevproject.Budget.Model;


/**
 *  this class is to reprsent the budget,
 *  what are the items you buy?
 *  what is their cost if you get paid by weekly?
 *  what is the total amount you spend monthly?
 *  what is the expected tax on all this spending?
 */

public class Item {

    // what is the item?
    //what is the frequency?
    //weekly, byweekly, monthly, quartely, half year, annually?
    //what date?
    //what is the cost of the item?
    // outgoing $

    private Integer id;
    private String nameOfItem;
    private Double priceOfItem;
    private Integer category;
    private Integer frequencyOfPurchase;
        // i dont think this is going to be popular use.

    private Integer contractLength;
    private Double yearlyRenewalFee;
    private Double cancelationFee;

    // this is the foren key
    private Integer forenKey;



    public Item(String nameOfItem, Double priceOfItem, Integer category, Integer contractLength, Double yearlyRenewalFee, Double cancelationFee, Integer forenKey) {
        this.nameOfItem = nameOfItem;
        this.priceOfItem = priceOfItem;
        this.category = category;
        this.contractLength = contractLength;
        this.yearlyRenewalFee = yearlyRenewalFee;
        this.cancelationFee = cancelationFee;
        this.forenKey = forenKey;
    }


// constructor, getters and setters below
    // put methods above.

    public Item(Integer id, String nameOfItem, Integer category,
                 Double priceOfItem,
                Double yearlyRenewalFee, Double cancelationFee,
                Integer contractLength, Integer forenKey
    ) {
        this.id= id;
        this.nameOfItem = nameOfItem;
        this.category = category;
//        this.frequencyOfPurchase = frequencyOfPurchase;
//        Integer frequencyOfPurchase,
        this.priceOfItem = priceOfItem;
        this.yearlyRenewalFee = yearlyRenewalFee;
        this.cancelationFee = cancelationFee;
        this.contractLength= contractLength;
        this.forenKey = forenKey;
    }



    public Item(Integer id, String nameOfItem, Integer category,
                Integer frequencyOfPurchase, Double priceOfItem,
                Double yearlyRenewalFee, Double cancelationFee,
                Integer contractLength
    ) {
        this.id = id;
        this.nameOfItem = nameOfItem;
        this.category = category;
        this.frequencyOfPurchase = frequencyOfPurchase;
        this.priceOfItem = priceOfItem;
        this.yearlyRenewalFee = yearlyRenewalFee;
        this.cancelationFee = cancelationFee;
        this.contractLength= contractLength;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getFrequencyOfPurchase() {
        return frequencyOfPurchase;
    }

    public void setFrequencyOfPurchase(Integer frequencyOfPurchase) {
        this.frequencyOfPurchase = frequencyOfPurchase;
    }

    public Double getPriceOfItem() {
        return priceOfItem;
    }

    public void setPriceOfItem(Double priceOfItem) {
        this.priceOfItem = priceOfItem;
    }

    public Double getYearlyRenewalFee() {
        return yearlyRenewalFee;
    }

    public void setYearlyRenewalFee(Double yearlyRenewalFee) {
        this.yearlyRenewalFee = yearlyRenewalFee;
    }

    public Double getCancelationFee() {
        return cancelationFee;
    }

    public void setCancelationFee(Double cancelationFee) {
        this.cancelationFee = cancelationFee;
    }

    public double getContractLength() {
        return contractLength;
    }

    public void setContractLength(int contractLength) {
        this.contractLength = contractLength;
    }

    public void setContractLength(Integer contractLength) {
        this.contractLength = contractLength;
    }
    public Integer getForenKey() {
        return forenKey;
    }

    public void setForenKey(Integer forenKey) {
        this.forenKey = forenKey;
    }

}