package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

public class BudgetPage extends AppCompatActivity {

//----- adapter stuff
    // if we can set up 3 buttons for differnt queries,
    // query 1: Sort by balance // query 2: Sort by yearly Total //  q 3:?
    private RecyclerView recyclerView; // i want this to show budget items.
    BudgetPageAdapter adapter;



//----- page stuff
    private TextView monthlyExp, yearlyExp, postTaxIncome, yearlyNet;
    private FloatingActionButton fab;
    private ProjectDb myDb;
    private TabLayout tabCategories;

    List<Item> housingList = new ArrayList<>();
    List<Item> utilityList = new ArrayList<>();
    List<Item> transportList = new ArrayList<>();
    List<Item> foodList = new ArrayList<>();
    List<Item> entertainmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_activity);

        makeAssoications();
//        LoadModelData();

        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= myDb.getUserById(s.getString("username",""));
        List<Item> myItems= myDb.item_getAll(foreignKey);

        for (int i = 0; i < myItems.size(); i++){
            if(myItems.get(i).getCategory() == 0){
                housingList.add(myItems.get(i));
            } else if (myItems.get(i).getCategory() == 1) {
                utilityList.add(myItems.get(i));
            } else if (myItems.get(i).getCategory() == 2) {
                transportList.add(myItems.get(i));
            } else if (myItems.get(i).getCategory() == 3) {
                foodList.add(myItems.get(i));
            } else {
                entertainmentList.add(myItems.get(i));
            }

        }

        recyclerView = findViewById(R.id.bud_recyclerView);
        adapter = new BudgetPageAdapter(housingList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tabCategories.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        adapter.setMyItems(housingList);
                        recyclerView.setAdapter(adapter);
                        break;
                    case 1:
                        adapter.setMyItems(utilityList);
                        recyclerView.setAdapter(adapter);
                        break;
                    case 2:
                        adapter.setMyItems(foodList);
                        recyclerView.setAdapter(adapter);
                        break;
                    case  3:
                        adapter.setMyItems(transportList);
                        recyclerView.setAdapter(adapter);
                        break;
                    case 4:
                        adapter.setMyItems(entertainmentList);
                        recyclerView.setAdapter(adapter);
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

    }

    private void makeAssoications(){

        monthlyExp= findViewById(R.id.bud_monthlyExp);
        yearlyExp= findViewById(R.id.bud_yearlyExp);
        postTaxIncome= findViewById(R.id.bud_postTaxincome);
        //yearlyNet= findViewById(R.id.bud_yearlyNet);


        recyclerView = findViewById(R.id.bud_recyclerView);
        tabCategories = findViewById(R.id.tabCategories);
        fab=findViewById(R.id.bud_fab);
        myDb= new ProjectDb(BudgetPage.this);


    }
}