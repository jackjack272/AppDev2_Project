package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appdevproject.R;

public class LandingPage extends AppCompatActivity {
    private TextView welcomeBanner;

    private Button edit, budget, invest;

    private TextView income, expense, remaining;



    private  SharedPreferences username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        makeAssociations();

        welcomeUser("Welcome! ");




        //in page nav
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, EditUserPage.class));
            }
        });

        budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this, BudgetPage.class));
            }
        });
        invest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent());
            }
        });

    }

    private void makeAssociations(){
        income= findViewById(R.id.home_income);
        expense= findViewById(R.id.home_expenses);
        remaining=findViewById(R.id.home_moneyRemaning);
        welcomeBanner= findViewById(R.id.welcomeBanner);

        edit= findViewById(R.id.home_editUser);
        budget=findViewById(R.id.home_budget);
        invest=findViewById(R.id.home_invest);

        username= getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
    }

    private void welcomeUser(String s){
        welcomeBanner.setText(s+" "+username.getString("username","failed to find username"));
    }






}