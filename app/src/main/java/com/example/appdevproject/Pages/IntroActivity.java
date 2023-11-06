package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appdevproject.Loans.Loans_old;
import com.example.appdevproject.R;

public class IntroActivity extends AppCompatActivity {

    TextView txtBudget;
    TextView txtDebt;
    TextView txtInvestment;
    TextView txtCharts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        txtBudget = findViewById(R.id.txtBudget);
        txtDebt = findViewById(R.id.txtDebt);
        txtInvestment = findViewById(R.id.txtInvestment);
        txtCharts = findViewById(R.id.txtStat);

        txtBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, BudgetPage.class));
            }
        });

        txtDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, Loans_old.class));
            }
        });
        txtInvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, InvestPage.class));
            }
        });
        txtCharts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, ChartsActivity.class));
            }
        });
    }
}