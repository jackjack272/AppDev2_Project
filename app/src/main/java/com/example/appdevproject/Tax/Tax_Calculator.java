package com.example.appdevproject.Tax;

public class Tax_Calculator {
    //cant belive i git clean -f all the content from this file away..

    //get tax consequence
    public static double bondTaxConsequence() {
        return 6.0;
    }

    public static double stockTaxConsequence() {
        //personal finc week4:"Dividend invome": owning stock that pays div
        return 4.0;
    }

    public static double debtTaxConsequence(Double amount) {
        return 5.0;
    }

    public static double incomeTaxConsequence(Double yearEarning) {
        //personal finc: week 4: "Tax Rates": tax consequence of income.

        if (yearEarning <= 12000) {
            return 0;
        } else if (yearEarning > 12001 && yearEarning <= 20000) {
            return yearEarning * .15;

        } else if (yearEarning > 48001 && yearEarning <= 95000) {
            return yearEarning * .3 + 7360;

        } else if (yearEarning > 95001 && yearEarning <= 148000) {

            return yearEarning * .4 + 21460;
        } else if (yearEarning > 148001 && yearEarning <= 210000) {

            return yearEarning * .45 + 42660;
        } else if (yearEarning > 210001) {

            return yearEarning * .50 + 70560;
        }

        return -1;
    }










}
