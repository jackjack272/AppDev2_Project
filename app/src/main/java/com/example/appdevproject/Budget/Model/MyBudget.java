package com.example.appdevproject.Budget.Model;


import android.content.Context;

import com.example.appdevproject.Budget.InterFaces.Budget;
import com.example.appdevproject.DataBase.ProjectDb;

import java.util.List;


/**
 * This is a list of all the objects that i buy on a monthly bases
 * all the data is summerised in this method.
 *
 */

public class MyBudget implements Budget {
    
    //need lab 15 to be able to add items to the table.


    private List<Item>  myItems;
    private String[] myCategories;
    private Integer userId;
    private ProjectDb myDb;

    public MyBudget(Context context, Integer id){
//         myCategories = getResources().getString(R.string.item_select_category);
        // why dose this not work?

        userId= id;
        myDb= new ProjectDb(context);
    }

    @Override
    public double getMonthlyExpense() {

        return 0;
    }

    @Override
    public double getToatlMonthlyExpense() {
        return 0;
    }

    @Override
    public double getTotalMonthlyIncome() {
        return 0;
    }

    @Override
    public double getMonthyRemaing() {
        return 0;
    }



}
