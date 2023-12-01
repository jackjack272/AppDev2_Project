package com.example.appdevproject.Tax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appdevproject.Investment.Models.Totals_Save;
import com.example.appdevproject.R;
import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Tax.Adapters.Tax_InvestRecyAdapter;
import com.example.appdevproject.Tax.Adapters.Tax_LabourAdapter;
import com.example.appdevproject.Tax.Fab.Tax_AddNewWage;
import com.example.appdevproject.Tax.Models.Tax_Income;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Tax_Page extends AppCompatActivity {

    private TextView labourFor, investFor, govWants;
    private RecyclerView labour_recycle, invest_recycle;
    private FloatingActionButton fab;
    private ProjectDb myDb;

    private RecyclerView.LayoutManager layoutManager, labourManager;

    private Tax_InvestRecyAdapter recyAdapter;
    private Tax_LabourAdapter labourAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        makeAssocications();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Tax_AddNewWage.newInstace()
                    .show(
                        getSupportFragmentManager(),
                        Tax_AddNewWage.TAG
                    );
            }
        });

//    //top recycler
        makeLabourAdapter();
//
//    //bottom recycler
        makeInvestedAdapter();
//



        sleepThread(750); //sleep so db can be queried befor this displays
        makeHeadings();
    }


    private void sleepThread(int mils) {
        try {Thread.sleep(mils);} catch (InterruptedException e) {e.printStackTrace();}
    }



    public void makeLabourAdapter(){

    //breaks when the db is empty.
        //not displaying
        List<Tax_Income> items= myDb.income_readAll(getForeighnkey());

        labourManager= new LinearLayoutManager(this);
        labour_recycle.setLayoutManager(labourManager);

        labourAdapter= new Tax_LabourAdapter();
        labourAdapter.setMyItems(items);
        labour_recycle.setAdapter(labourAdapter);

    }

    public void makeInvestedAdapter(){

        List<Totals_Save> myTaxableItems= myDb.totals_readTotal(getForeighnkey());

        layoutManager= new LinearLayoutManager(this);
        invest_recycle.setLayoutManager(layoutManager);

        recyAdapter= new Tax_InvestRecyAdapter();
        recyAdapter.setMyItems(myTaxableItems);
        invest_recycle.setAdapter(recyAdapter);
    }


    private void makeHeadings(){
    //from labour
        Double totalInc=0.0,totalLabtax=0.0, totalInvestTax=0.0;
        totalInc= labourAdapter.getYearlyIncome();
        totalLabtax= labourAdapter.getTotalLabourTax();

    //from investmentes
        totalInvestTax= recyAdapter.getNetTax();

        labourFor.setText(String.format("My labour: %.2f. My prize: %.2f",totalInc,totalLabtax));
        investFor.setText(String.format("I gained: %.2f",totalInvestTax));
        govWants.setText(String.format("Gov's cut: $%.2f", totalLabtax+totalInvestTax));
    }

    private void makeAssocications(){
//    textviews
        labourFor= findViewById(R.id.inc_labour);
        investFor= findViewById(R.id.inc_invest);
        govWants= findViewById(R.id.inc_govWants);

    //recyclers
        labour_recycle= findViewById(R.id.inc_labourRecycle);
        invest_recycle=findViewById(R.id.inc_investRecycle);

    //extra
        myDb= new ProjectDb(Tax_Page.this);
        fab= findViewById(R.id.inc_fab);
    }


    public int getForeighnkey() {
        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= myDb.getUserById(s.getString("username",""));
        return foreignKey;
    }


}