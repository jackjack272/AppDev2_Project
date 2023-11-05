package com.example.appdevproject.DataBase.Interfaces;

import com.example.appdevproject.Investment.Models.Sum;

public interface Totals {

        public Sum totals_GetBond(int forenKey);
        public Sum totals_GetDebt(int forenKey);
        public Sum totals_GetStock(int forenKey);



}
