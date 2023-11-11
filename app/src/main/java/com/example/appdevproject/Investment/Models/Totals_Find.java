package com.example.appdevproject.Investment.Models;

import android.content.Context;

import com.example.appdevproject.DataBase.ProjectDb;

public class Totals_Find

        implements com.example.appdevproject.Investment.Models.Interfaces.Totals
{
    ProjectDb myDb;
    Integer foreignKey;

    public Totals_Find(Context context,int foreignKey){
        myDb=new ProjectDb(context);
        this.foreignKey= foreignKey;
    }


    @Override
    public Totals_Save getBonds() {

        myDb.debt_readBonds(this.foreignKey);


        return new Totals_Save("Bonds",0.0,0.0,0.0);

    }

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
