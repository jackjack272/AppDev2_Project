package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdevproject.Investment.InvestmentActivity;
import com.example.appdevproject.Loans.Loans;
import com.example.appdevproject.R;

public class IntroActivity extends AppCompatActivity {

    TextView txtBudget;
    TextView txtDebt;
    TextView txtInvestment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        txtBudget = findViewById(R.id.txtBudget);
        txtDebt = findViewById(R.id.txtDebt);
        txtInvestment = findViewById(R.id.txtInvestment);

        txtBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, BudgetPage.class));
            }
        });

        txtDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, Loans.class));
            }
        });
        txtInvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, InvestmentActivity.class));
            }
        });
    }
}