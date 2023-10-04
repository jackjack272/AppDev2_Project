package com.example.appdevproject.Budget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appdevproject.R;

import java.util.concurrent.RecursiveAction;


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

public class Budget extends AppCompatActivity {

    private TextView monthlyExp, yearlyExp, postTaxIncome, yearlyNet;
    private Button taxPoriton;
    private RecyclerView recyclerView; // i want this to show budget items.
        // if we can set up 3 buttons for differnt queries,
            // query 1: Sort by balance // query 2: Sort by yearly Total //  q 3:?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        makeAssoications();


        taxPoriton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Budget.this, Income.class));
            }
        });

    }

    private void makeAssoications(){
        taxPoriton= findViewById(R.id.bud_taxBtn);



    }


}