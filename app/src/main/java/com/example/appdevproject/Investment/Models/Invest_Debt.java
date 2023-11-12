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


    //show the repayment/amortization  schedule


    //what if i pay extra $50 per month?


    //what is the total value of this loan?


    //what should my payments be to pay off the loan in 10 months?




    public double getMonthlyInterest(){
        //compute effective annual rate /12
//        https://www.investopedia.com/terms/e/effectiveinterest.asp

        double  interestRate= (1+ (this.interestRate/(this.compoundsPerYear*this.loanTermInMonths)) );
        interestRate= Math.pow(interestRate,(this.compoundsPerYear*this.loanTermInMonths));
        interestRate-=1;

        return interestRate/12;

    }

    public double getNewMarketValue(){
        if (isDebt) {
            //debt increases
//            return this.amountBorred +getAnnualCompoundRate() *this.amountBorred/12;
            return  -1.0;
        }
//        bonds differnt formula

        return -23.0;
    }




    public double getAnnualCompoundRate() {
        //ear formula https://corporatefinanceinstitute.com/resources/commercial-lending/effective-annual-interest-rate-ear/#:~:text=Apply%20the%20EAR%20Formula%3A%20EAR,n%20%3D%20Compounding%20periods

        //EAR=(1+interest/number of compounds per period) ^ num compounds per period -1
        // (1+10%/2 )^2 -1 =.1025

        double xx=(1+ (this.interestRate/100) / this.compoundsPerYear );
        xx=Math.pow ( xx,this.compoundsPerYear);
        xx-=1;
        xx=xx*100;
        return xx;
    }

    public double getMarketValue(Double currentMarketRate) {
        /**
         * Suppose you have a bond with the following characteristics:
         *
         * Coupon Rate: 5%
         * Face Value: $1,000
         * Time to Maturity: 5 years
         * YTM (market interest rate): 4%
         * Calculate the present value of coupon payments:
         *
         * Coupon Payment = (0.05 x $1,000) / 1 = $50
         * n = 5 years
         * YTM = 0.04 (4% expressed as a decimal)
         * PV(Coupon Payments) = $50 x [1 - (1 + 0.04)^(-5)] / 0.04 ≈ $215.47
         *
         * Calculate the present value of the face value:
         *
         * PV(Face Value) = $1,000 / (1 + 0.04)^5 ≈ $822.70
         * Add the two present values to get the market value:
         *
         * Market Value = $215.47 + $822.70 ≈ $1,038.17
         * So, the market value of the bond in this example is approximately $1,038.17.
         */

        Double  compounPayment, n, ytm,pv, marketvalue;
        compounPayment= (this.getInterestRate()/100 ) * this.getAmountBorred()/this.getCompoundsPerYear();
        n= Double.valueOf(this.getCompoundsPerYear() *(this.getLoanTermInMonths()/12));


        pv=compounPayment *(1-  Math.pow ( (1+currentMarketRate),(n *-1) ) ) /n;
        //coupon payments

        pv+=Math.pow( this.getAmountBorred() /(1+currentMarketRate),n);
        //face value

        return pv;
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

