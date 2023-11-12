package com.example.appdevproject.Investment.Models.Interfaces;

import com.example.appdevproject.Investment.Models.Totals_Save;

public interface Totals {


//total investment
        //total investment growth
        //total debt growth


//total expenses


//total monthly take home.

//total income -  total tax brakcet



//total yearly take home

        public Totals_Save getBonds(Double marketValue);
        public Totals_Save getDebt();
        public Totals_Save getStock();
        public Totals_Save getExpenses();
        public Totals_Save getIncome();
        public Totals_Save getTax();



}
