package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appdevproject.Budget.Adapter.BudgetAdapterTwo;
import com.example.appdevproject.Budget.Fab.Budget_AddNewItem;
import com.example.appdevproject.Budget.Model.Item;
import com.example.appdevproject.R;
import com.example.appdevproject.DataBase.ProjectDb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * this class is going to hold budget item.
 *
 * button is going to take us to tax calculator
 *
 *
 *  these are goin to be columns in the db.
 *  #1= housing
 *      # kitchen supplies
 *      # Barthroom supplies
 *      # bedroom supplies. // sounds questionable lol
 *  #
 *
 *
 *
 *  #2= transporation
 *  #3=
 *
 *  // for the budget to be effective it needs to tell you if youre loosing money yearly
 *      //need to tell you your tax expectations before deductions and other considerations for a baseline.
 *
 */

public class BudgetPage extends AppCompatActivity {

//----- adapter stuff
    // if we can set up 3 buttons for differnt queries,
    // query 1: Sort by balance // query 2: Sort by yearly Total //  q 3:?
    private RecyclerView recyclerView; // i want this to show budget items.
    RecyclerView.LayoutManager layoutManager;
    private BudgetAdapterTwo budgetAdapter;


//----- page stuff
    private TextView monthlyExp, yearlyExp, postTaxIncome, yearlyNet;
    private FloatingActionButton fab;
    private ProjectDb myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_activity);

        makeAssoications();

        setAdapter();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Budget_AddNewItem.newInstace()
                    .show(
                        getSupportFragmentManager(),
                        Budget_AddNewItem.TAG
            //category number item are hard coded in AddNewItemToBudget
                    );
            }
        });

        //recucler view touch helper need to be an implemented class
        //based on lab15 class of same name.
//        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(new Budget_RecylerViewTouchHelper(budgetAdapter));
//        itemTouchHelper.attachToRecyclerView(recyclerView);


    }



    private void setAdapter(){
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        budgetAdapter= new BudgetAdapterTwo(BudgetPage.this);

        //set the item s
        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= myDb.getUserById(s.getString("username",""));
        List<Item> myItems= myDb.item_getAll(foreignKey);
        budgetAdapter.setItems(myItems);


        recyclerView.setAdapter(budgetAdapter);

    }


    private void makeAssoications(){

        monthlyExp= findViewById(R.id.bud_monthlyExp);
        yearlyExp= findViewById(R.id.bud_yearlyExp);
        postTaxIncome= findViewById(R.id.bud_postTaxincome);
        yearlyNet= findViewById(R.id.bud_yearlyNet);


        recyclerView= findViewById(R.id.bud_recyclerView);
        fab=findViewById(R.id.bud_fab);
        myDb= new ProjectDb(BudgetPage.this);

    }



    //this is old non working impl
//    private void setRecuclerAdapter(){
//        budgetAdapter= new BudgetAdapter(myDb, BudgetPage.this);
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(budgetAdapter);
//
//        SharedPreferences sharedPreferences=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
//        int foreignKey= myDb.getUserById(
//                sharedPreferences.getString("username",""));
//        List<Item> myItems= myDb.item_getAll(foreignKey);
//
//
//        budgetAdapter.setItems(myItems);
//
//    }


}