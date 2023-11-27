package com.example.appdevproject.DataBase.Interfaces;

import static com.example.appdevproject.DataBase.Interfaces.Users.USER_ID;
import static com.example.appdevproject.DataBase.Interfaces.Users.USER_TABLE;

import com.example.appdevproject.Investment.Models.Invest_Stock;

public interface Stock {

    static final String STOCK_TABLE="stock";
    static final String STOCK_ID="id";
    static final String STOCK_FORENKEY=USER_ID+"_foreign";



    static final String STOCK_TICKER="ticker";
    static final String STOCK_PRICE="price";
    static final String STOCK_DIV="monthly_div";
    static final String STOCK_LASTSIXMONTHCLOSE="halfyear_close";
    static final String STOCK_LASTSIXMONTHOPEN="halfyear_open";
    static final String STOCK_QTY ="quantity";




    static final String MAKE_STOCK_TABLE = "CREATE TABLE "+STOCK_TABLE+" ("
            + STOCK_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "

            +STOCK_TICKER+" TEXT,"
            +STOCK_PRICE+ " REAL,"
            +STOCK_QTY +" INTEGER,"
            +STOCK_DIV +" REAL,"
            +STOCK_LASTSIXMONTHOPEN+ " REAL,"
            +STOCK_LASTSIXMONTHCLOSE+ " REAL,"

            +STOCK_FORENKEY+" INTEGER, "
            +"FOREIGN KEY ("+USER_ID+") REFERENCES "+USER_TABLE+" ("+USER_ID+")" +
            ");"
            ;




    public void stock_saveOne(Invest_Stock stock);



}
