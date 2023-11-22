package com.example.appdevproject.Debt_Repayment.Models;

import com.example.appdevproject.Investment.Models.Invest_Debt;

import java.util.ArrayList;
import java.util.List;

public class RepaySchedualeItem {

    private Integer repaymentNumber;
    private Double paymentAmount;
    private Double toInteret;
    private Double toPrinciple;
    private Double totalAmountRepay;
    private Double newPrice;


    public RepaySchedualeItem(Integer repaymentNumber, Double toInteret, Double toPrinciple, Double amountRepay, Double newPrice, Double paymentAmount) {
        this.repaymentNumber = repaymentNumber;
        this.toInteret = toInteret;
        this.toPrinciple = toPrinciple;
        this.totalAmountRepay = amountRepay;
        this.newPrice = newPrice;
        this.paymentAmount = paymentAmount;
    }

    public static List<RepaySchedualeItem> getRepaymentScheduale(List<Invest_Debt> myDebts, Double min_repayment){
        // consoladate all the items into 1 big loan.

        double totalPaymentToDebt=0.0;

        double totalPrinciple=0.0, totalMonthly=0.0, avgInterestRate=0.0, avgLoanLen=0.0;
        for(Invest_Debt one: myDebts){
            totalPrinciple+= one.getAmountBorred();
            avgInterestRate+=one.getEffectiveInterestRate();

//            totalMonthly+=one.getMonthlyPatment();
//            avgLoanLen=one.getLoanTermInMonths();
        }
//        avgLoanLen=avgLoanLen/myDebts.size();
        avgInterestRate=avgInterestRate/myDebts.size();

        //for this 1 big loan...
        List<RepaySchedualeItem> entries=new ArrayList<>();

        double paymentAmount, toInterest,toPrinciple;
        double amountRepaied=0.0;

        for (int i=0; 0 <totalPrinciple ;i++){
            paymentAmount=min_repayment+50;
            toInterest=totalPrinciple*avgInterestRate/100/12;
            toPrinciple=paymentAmount-toInterest;
            totalPrinciple -=toPrinciple;
            amountRepaied+=paymentAmount;

            entries.add(
                    new RepaySchedualeItem(i,toInterest,toPrinciple,amountRepaied,totalPrinciple,paymentAmount)
            );
        }

        if (entries.size()==0) {
            return null;
        }
        return  entries;
    }


    public Integer getRepaymentNumber() {
        return repaymentNumber;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public Double getToInteret() {
        return toInteret;
    }

    public Double getToPrinciple() {
        return toPrinciple;
    }

    public Double getTotalAmountRepay() {
        return totalAmountRepay;
    }

    public Double getNewPrice() {
        return newPrice;
    }
}







