package com.example.appdevproject.Investment.Money;

import com.example.appdevproject.Investment.Interfaces.Invest_Borrow;

public class Invest_Loan extends Invest_Model implements Invest_Borrow {

    public Invest_Loan(Double amount, Double interestRate, Integer compoundsPerYear, Double months) {
        super(amount, interestRate, compoundsPerYear, months);
    }



    @Override
    public void showRepaymentSchedule(double Amount) {

    }

    @Override
    public void showRepayIn(int months) {

    }



}
