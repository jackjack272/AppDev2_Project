package com.example.appdevproject.Investment.Models;

import android.content.Context;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Fragments.Invest_fragmentBond;
import com.example.appdevproject.Investment.Models.Interfaces.Bonds;

import java.util.List;

public class Totals_Find

        implements com.example.appdevproject.Investment.Models.Interfaces.Totals
{
    ProjectDb myDb;
    Integer foreignKey;

    public Totals_Find(Context context,int foreignKey){
        myDb=new ProjectDb(context);
        this.foreignKey= foreignKey;
    }

    public Totals_Save getBonds(Double marketValue) {
        List<Invest_Debt> myBonds= myDb.debt_readBonds(this.foreignKey);

        if(myBonds.size() ==0){
            return new Totals_Save("Bonds",0.0,0.0,0.0);
        }

        Double monthylInterest=0.0, totalAmount=0.0, amountChanged=0.0;


        for(Invest_Debt xx: myBonds){


        }
        monthylInterest=monthylInterest/myBonds.size();// get the avg per bond

        return new Totals_Save("Bonds",monthylInterest,totalAmount,amountChanged);


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
