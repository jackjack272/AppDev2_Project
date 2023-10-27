package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appdevproject.Budget.AddNewItemToBudget;
import com.example.appdevproject.Budget.Adapter.BudgetAdapter;
import com.example.appdevproject.Budget.Budget_RecylerViewTouchHelper;
import com.example.appdevproject.R;
import com.example.appdevproject.Utility.ProjectDb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


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

    private TextView monthlyExp, yearlyExp, postTaxIncome, yearlyNet;
    private Button taxPoriton;


        // if we can set up 3 buttons for differnt queries,
            // query 1: Sort by balance // query 2: Sort by yearly Total //  q 3:?
    private RecyclerView recyclerView; // i want this to show budget items.
    private FloatingActionButton fab;
    private ProjectDb myDb;
    private BudgetAdapter budgetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_activity);
        makeAssoications();


        //next section
        taxPoriton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BudgetPage.this, IncomePage.class));
            }
        });


//        setRecuclerAdapter();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewItemToBudget.newInstace()
                    .show(
                        getSupportFragmentManager(),
                        AddNewItemToBudget.TAG
            //category number item are hard coded in AddNewItemToBudget
                    );
            }
        });

        //recucler view touch helper need to be an implemented class
        //based on lab15 class of same name.
        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(new Budget_RecylerViewTouchHelper(budgetAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }




    private void setRecuclerAdapter(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(budgetAdapter);

        SharedPreferences sharedPreferences=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= myDb.getUserById(
                sharedPreferences.getString("username",""));
        budgetAdapter.setItems(foreignKey);
    }


    private void makeAssoications(){
        taxPoriton= findViewById(R.id.bud_taxBtn);
        monthlyExp= findViewById(R.id.bud_monthlyExp);
        yearlyExp= findViewById(R.id.bud_yearlyExp);
        postTaxIncome= findViewById(R.id.bud_postTaxincome);
        yearlyNet= findViewById(R.id.bud_yearlyNet);


        recyclerView= findViewById(R.id.bud_recyclerView);
        fab=findViewById(R.id.bud_fab);
        myDb= new ProjectDb(BudgetPage.this);
        budgetAdapter= new BudgetAdapter(myDb, BudgetPage.this);

    }


}