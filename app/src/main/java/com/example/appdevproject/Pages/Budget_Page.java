package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appdevproject.Budget.Adapter.BudgetPageAdapter;
import com.example.appdevproject.Budget.Fab.Budget_AddNewItem;
import com.example.appdevproject.Budget.Model.Item;
import com.example.appdevproject.R;
import com.example.appdevproject.DataBase.ProjectDb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
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

public class Budget_Page extends AppCompatActivity {

//----- adapter stuff
    // if we can set up 3 buttons for differnt queries,
    // query 1: Sort by balance // query 2: Sort by yearly Total //  q 3:?
    private RecyclerView recyclerView; // i want this to show budget items.
    BudgetPageAdapter adapter;



//----- page stuff
    private TextView monthlyExp, yearlyExp, postTaxIncome, yearlyNet, totalExp;
    private FloatingActionButton fab,chart_fab;
    private ProjectDb myDb;
    private TabLayout tabCategories;

    List<Item> housingList = new ArrayList<>();
    List<Item> utilityList = new ArrayList<>();
    List<Item> transportList = new ArrayList<>();
    List<Item> foodList = new ArrayList<>();
    List<Item> entertainmentList = new ArrayList<>();

    double totHousing = 0;
    double totUtility = 0;
    double totTransport = 0;
    double totFood = 0;
    double totEntertain = 0;
    double totExpenses = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_activity);

        makeAssoications();

        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= myDb.getUserById(s.getString("username",""));
        List<Item> myItems= myDb.item_getAll(foreignKey);

        for (int i = 0; i < myItems.size(); i++){
            if(myItems.get(i).getCategory() == 0){
                housingList.add(myItems.get(i));
                totHousing = totHousing + myItems.get(i).getPriceOfItem();
            } else if (myItems.get(i).getCategory() == 1) {
                utilityList.add(myItems.get(i));
                totUtility = totUtility + myItems.get(i).getPriceOfItem();
            } else if (myItems.get(i).getCategory() == 2) {
                transportList.add(myItems.get(i));
                totTransport = totTransport + myItems.get(i).getPriceOfItem();
            } else if (myItems.get(i).getCategory() == 3) {
                foodList.add(myItems.get(i));
                totFood = totFood + myItems.get(i).getPriceOfItem();
            } else {
                entertainmentList.add(myItems.get(i));
                totEntertain = totEntertain + myItems.get(i).getPriceOfItem();
            }
        }

        totExpenses = totHousing + totUtility + totTransport + totFood + totEntertain;

        recyclerView = findViewById(R.id.bud_recyclerView);
        adapter = new BudgetPageAdapter(housingList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        totalExp.setText("Total Housing Expenses: " + totHousing + " $");
        monthlyExp.setText("Total Expenses: " + totExpenses + " $");


        tabCategories.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        adapter.setMyItems(housingList);
                        recyclerView.setAdapter(adapter);
                        totalExp.setText("Total Housing Expenses: " + totHousing + " $");
                        break;
                    case 1:
                        adapter.setMyItems(utilityList);
                        recyclerView.setAdapter(adapter);
                        totalExp.setText("Total Utility Expenses: " + totUtility + " $");
                        break;
                    case 2:
                        adapter.setMyItems(foodList);
                        recyclerView.setAdapter(adapter);
                        totalExp.setText("Total Food Expenses: " + totFood + " $");
                        break;
                    case  3:
                        adapter.setMyItems(transportList);
                        recyclerView.setAdapter(adapter);
                        totalExp.setText("Total Transportation Expenses: " + totTransport + " $");
                        break;
                    case 4:
                        adapter.setMyItems(entertainmentList);
                        recyclerView.setAdapter(adapter);
                        totalExp.setText("Total Entertainment Expenses: " + totEntertain + " $");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Budget_AddNewItem.newInstace()
                    .show(
                        getSupportFragmentManager(),
                        Budget_AddNewItem.TAG
                    );
            }
        });

        chart_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Budget_Page.this, Budget_ChartPage.class);
                i.putExtra("totHousing",totHousing);
                i.putExtra("totUtility",totUtility);
                i.putExtra("totTransport",totTransport);
                i.putExtra("totFood",totFood);
                i.putExtra("totEntertain",totEntertain);
                startActivity(i);
            }
        });

    }

    private void makeAssoications(){

        monthlyExp= findViewById(R.id.bud_monthlyExp);

        recyclerView = findViewById(R.id.bud_recyclerView);
        fab = findViewById(R.id.bud_fab);
        myDb= new ProjectDb(Budget_Page.this);


//        yearlyExp= findViewById(R.id.bud_yearlyExp);
//        postTaxIncome= findViewById(R.id.bud_postTaxincome);
//        yearlyNet= findViewById(R.id.bud_yearlyNet);
        totalExp = findViewById(R.id.txtTotal);
        tabCategories = findViewById(R.id.tabCategories);
        chart_fab = findViewById(R.id.bud_chart_fab);

    }
}