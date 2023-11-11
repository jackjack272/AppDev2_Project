package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appdevproject.Loans.Loans_old;
import com.example.appdevproject.R;

public class IntroActivity extends AppCompatActivity {

    CardView cardBudget;
    CardView cardDebt;
    CardView cardInvestment;
    CardView cardCharts;
    TextView textUserName;
    ImageButton btnLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        cardBudget = findViewById(R.id.BudgetCard);
        cardDebt = findViewById(R.id.DebtCard);
        cardInvestment = findViewById(R.id.InvestmentCard);
        cardCharts = findViewById(R.id.StatCard);
        textUserName = findViewById(R.id.textUserName);
        btnLogOut = findViewById(R.id.btnLogout);

        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String user= s.getString("username","");
        textUserName.setText("Hello " + user);

        cardBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, BudgetPage.class));
            }
        });

        cardDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, Loans_old.class));
            }
        });
        cardInvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, InvestPage.class));
            }
        });
        cardCharts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, ChartsActivity.class));
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, RegistrationPage.class));
            }
        });

    }
}