package com.example.appdevproject.Investment.Models;

import android.content.Context;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Fragments.Invest_fragmentBond;
import com.example.appdevproject.Investment.Models.Interfaces.Bonds;

import java.util.List;

public class Totals_Find

        implements com.example.appdevproject.Investment.Models.Interfaces.Totals
{

    /**
     * This class will find the totals,
     *
     */

    ProjectDb myDb;
    Integer foreignKey;

    public Totals_Find(Context context,int foreignKey){
        myDb=new ProjectDb(context);
        this.foreignKey= foreignKey;
    }

    public Totals_Save getBonds() {
        List<Invest_Debt> myBonds= myDb.debt_readBonds(this.foreignKey);


        Double monthylInterest=0.0, totalAmount=0.0, amountChanged=0.0;

        if(myBonds.size() ==0){
            return new Totals_Save("Bonds",monthylInterest,totalAmount,amountChanged);
        }

        for(Invest_Debt xx: myBonds){
            totalAmount+=xx.getAmountBorred();
            amountChanged+= (xx.valueAtMaturity()- xx.getAmountBorred()) /xx.getLoanTermInMonths(); //in 5 years i get $500 but this year i get 1/5
            monthylInterest+=xx.getEffectiveInterestRate();
        }

        monthylInterest=monthylInterest/myBonds.size();// get the avg per bond

        return new Totals_Save("Bonds",monthylInterest,totalAmount,  amountChanged);
    }
    //get bonds(1) // return the totals for just that 1 bond.




    @Override
    public Totals_Save getDebt() {
        return new Totals_Save("Debt",0.0,0.0,0.0);

    }

    @Override
    public Totals_Save getStock() {


        return new Totals_Save("Stock",0.0,0.0,0.0);
    }



    @Override
    public Totals_Save getExpenses() {
        return null;
    }

    @Override
    public Totals_Save getIncome() {
        return null;
    }

    @Override
    public Totals_Save getTax() {
        return null;
    }

}
