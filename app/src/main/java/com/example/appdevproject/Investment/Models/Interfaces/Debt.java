package com.example.appdevproject.Investment.Models.Interfaces;

import com.example.appdevproject.Debt_Repayment.Models.RepaySchedualeItem;
import com.example.appdevproject.Investment.Models.Invest_Debt;

import java.util.List;

public interface Debt {

    public double getEffectiveInterestRate();
    public double paymentPerCompound();

    public double valueAtMaturity();


    public static List<RepaySchedualeItem> getRepaymentScheduale(List<Invest_Debt> myDebts) {
        return null;
    }


}
