package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appdevproject.Loans.Loans_old;
import com.example.appdevproject.R;

public class IntroActivity extends AppCompatActivity {

    CardView cardBudget;
    CardView cardDebt;
    CardView cardInvestment;
    CardView cardCharts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        cardBudget = findViewById(R.id.BudgetCard);
        cardDebt = findViewById(R.id.DebtCard);
        cardInvestment = findViewById(R.id.InvestmentCard);
        cardCharts = findViewById(R.id.StatCard);

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
    }
}