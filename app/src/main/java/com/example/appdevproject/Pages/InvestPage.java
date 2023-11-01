package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.appdevproject.Investment.Fab.Invest_AddNewInvest;
import com.example.appdevproject.Investment.Invest_Db;
import com.example.appdevproject.Investment.Invest_AddNew_old;
import com.example.appdevproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class InvestPage extends AppCompatActivity {
    Button btnAddNew;
    TextView marketVale;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);

        makeAssociations();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Invest_AddNewInvest.newInstace()
                    .show(
                        getSupportFragmentManager(),
                        Invest_AddNewInvest.TAG
                    );
            }
        });
    }

    public void makeAssociations(){
        fab=findViewById(R.id.invest_fab_addOne);
    }



    public void ogImpl(){
        Invest_Db db = new Invest_Db(this);
        ArrayList<HashMap<String, String>> investmentList = db.GetInvestments();
        ListView lv = (ListView) findViewById(R.id.invest_list);

        ListAdapter adapter = new SimpleAdapter(
                InvestPage.this,
                investmentList,
                R.layout.list_row,
                new String[]{"investname","investtype","investamount"},
                new int[]{R.id.name, R.id.type, R.id.amount}
        );

        lv.setAdapter(adapter);
//        btnAddNew = findViewById(R.id.btnAddInves);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InvestPage.this, Invest_AddNew_old.class));

            }
        });

    }


}