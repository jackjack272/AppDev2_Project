package com.example.appdevproject.Budget.InterFaces;

public interface Budget {

    //get the month's expenses
    public double getMonthlyExpense();

    //get total monthly cost: debt+ expenses
    public double getToatlMonthlyExpense();


    //show monthly income : investment dividends + income + bonds interest
    public double getTotalMonthlyIncome();


    //show the remainder for the month.
    public double getMonthyRemaing();



}
