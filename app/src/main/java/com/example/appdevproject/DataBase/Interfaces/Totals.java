package com.example.appdevproject.DataBase.Interfaces;

import static com.example.appdevproject.DataBase.Interfaces.Users.USER_ID;
import static com.example.appdevproject.DataBase.Interfaces.Users.USER_TABLE;

import com.example.appdevproject.Investment.Models.Totals_Save;

import java.util.List;


public interface Totals {

        public static final Integer TOTAL_BOND_PK=1;
        public static final Integer TOTAL_DEBT_PK=2;
        public static final Integer TOTAL_STOCK_PK=3;


        public void totals_saveOne(Totals_Save totals_save);
        public List<Totals_Save> totals_readTotal();
        public void totals_update(Totals_Save totals);
        public Boolean totals_empty();

        public Boolean totals_emptyBonds();

        public Boolean totals_emptyDebts();

        public Boolean totals_emptyStocks();



        public static final String TOTALS_TABLE="totals";

        public static final String TOTALS_ID="id";

        public static final String TOTALS_FOREIGNKEY=USER_ID+"_foreign";



        public static final String TOTALS_GROWTH="growth"; // +9%

        public static final String TOTALS_AMOUNT="amount"; // $800

        public static final String TOTALS_INTEREST="total_interest"; /// 800 * 9 // $880




         public static final String makeToatls ="CREATE TABLE "+TOTALS_TABLE
                +"("
                +TOTALS_ID+" INTEGER PRIMARY KEY, " // AUTOINCREMENT, "
                +TOTALS_AMOUNT +" REAL, "
                +TOTALS_GROWTH+" REAL,"
                +TOTALS_INTEREST+" REAL,"

                + TOTALS_FOREIGNKEY +" INTEGER, "
                + "FOREIGN KEY (" + USER_ID + ") " +
                "REFERENCES " + USER_TABLE + "(" + USER_ID + ")"
                + ")";  // Add a space before the closing parenthesis




}
