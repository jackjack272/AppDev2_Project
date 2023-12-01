package com.example.appdevproject.DataBase.Interfaces;

import static com.example.appdevproject.DataBase.Interfaces.Users.USER_ID;
import static com.example.appdevproject.DataBase.Interfaces.Users.USER_TABLE;

import com.example.appdevproject.Investment.Models.Invest_Debt;

import java.util.List;

public interface Debts {
    static final String DEBT_TABLE="loans";
    static final String DEBT_ID="id";
    static final String DEBT_NAME="name";
    static final String DEBT_AMOUNTBORROWED="amount_borrowed";
    static final String DEBT_INTERESTRATE="interest_rate";
    static final String DEBT_COMPOUNDSPERYEAR="yearly_compounds";
    static final String DEBT_LOANTERM="loan_term_months";
    static final String DEBT_ISDEBT="isdebt";
    static final String DEBT_FORENKEY=USER_ID+"_foreign";


    static final String MAKE_DEBT_TABLE = "CREATE TABLE "+DEBT_TABLE+" ("
            + DEBT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +DEBT_NAME+" TEXT,"
            +DEBT_AMOUNTBORROWED+ " REAL,"
            +DEBT_COMPOUNDSPERYEAR+ " INTEGER,"
            +DEBT_INTERESTRATE+ " REAL, "
            +DEBT_LOANTERM+ " INTEGER, "
            +DEBT_ISDEBT + " INTEGER,"
            +DEBT_FORENKEY+" INTEGER, "
            +"FOREIGN KEY ("+USER_ID+") REFERENCES "+USER_TABLE+" ("+USER_ID+")" +
            ");"
            ;




    public void debt_makeOne(Invest_Debt debt);
    Invest_Debt debt_readOne(int id);
    public List<Invest_Debt> debt_readDebt(int foreignKey);
    //bonds have the same properties as debt.
    public List<Invest_Debt> debt_readBonds(int foreignKey);
    public void debt_updateOne( Invest_Debt debt);
    public void debt_deleteOne(int position);
}
