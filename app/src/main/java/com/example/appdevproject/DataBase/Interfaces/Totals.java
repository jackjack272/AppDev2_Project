package com.example.appdevproject.DataBase.Interfaces;

import static com.example.appdevproject.DataBase.Interfaces.Users.USER_ID;
import static com.example.appdevproject.DataBase.Interfaces.Users.USER_TABLE;

import java.util.List;


public interface Totals {



        public void saveTotal(Totals totals);
        public List<Totals> readTotal();
        public void updateTotal(Totals totals);





        public static final String TOTALS_TABLE="totals";

        public static final String TOTALS_ID="id";

        public static final String TOTALS_FOREIGNKEY=USER_ID+"_foreign";

        public static final String TOTALS_GROWTH="growth"; // +9%

        public static final String TOTALS_AMOUNT="amount"; // $800

        public static final String TOTALS_INTEREST="total_interest"; /// 800 * 9 // $880




         public static final String makeToatls ="CREATE TABLE "+TOTALS_TABLE
                +"("
                +TOTALS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TOTALS_AMOUNT +" REAL, "
                +TOTALS_GROWTH+" REAL,"
                +TOTALS_INTEREST+" REAL,"

                + TOTALS_FOREIGNKEY +" INTEGER, "
                + "FOREIGN KEY (" + USER_ID + ") " +
                "REFERENCES " + USER_TABLE + "(" + USER_ID + ")"
                + ")";  // Add a space before the closing parenthesis




}
