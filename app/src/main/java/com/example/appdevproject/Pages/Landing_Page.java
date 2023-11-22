package com.example.appdevproject.Pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appdevproject.Debt_Repayment.Debt_Repayment;
import com.example.appdevproject.Investment.Invest_Page;
import com.example.appdevproject.R;
import com.example.appdevproject.Tax.Tax_Page;
import com.example.appdevproject.User.Edit_UserPage;
import com.example.appdevproject.User.Registration_Page;
import com.google.android.material.button.MaterialButton;

public class Landing_Page extends AppCompatActivity {

    CardView cardBudget, cardDebt,cardInvestment,cardCharts, taxCard;

    TextView textUserName;

    ImageButton btnLogOut;

    MaterialButton editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        makeAssociations();

        setHeading();

        myNav(); //temp nav



    }


//this is lab 11, supposed to make a drop down menue...
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.navigation,menu);
        return  true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.navigation_budget) {
            startActivity(new Intent(Landing_Page.this, Budget_Page.class));
        }else{
            return super.onOptionsItemSelected(item);
        }

        //fill it out once it works for 1.
        return  true;
    }
//---------------pls dont delete id like some easy nice navigation for app.




    public void myNav(){

        taxCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Landing_Page.this, Tax_Page.class));
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Landing_Page.this, Edit_UserPage.class));
            }
        });
        cardBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Landing_Page.this, Budget_Page.class));
            }
        });
        cardDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Landing_Page.this, Debt_Repayment.class));
            }
        });
        cardInvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Landing_Page.this, Invest_Page.class));
            }
        });
        cardCharts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Landing_Page.this, Charts_Page.class));
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Landing_Page.this, Registration_Page.class));
            }
        });




    }
    public void setHeading(){
        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String user= s.getString("username","");
        textUserName.setText("Hello " + user);
    }
    public void makeAssociations(){
        cardBudget = findViewById(R.id.BudgetCard);
        cardDebt = findViewById(R.id.DebtCard);
        cardInvestment = findViewById(R.id.InvestmentCard);
        cardCharts = findViewById(R.id.StatCard);
        textUserName = findViewById(R.id.textUserName);
        btnLogOut = findViewById(R.id.btnLogout);

        editProfile=findViewById(R.id.btnEditProfile);


        taxCard=findViewById(R.id.GoalCard);


    }
}