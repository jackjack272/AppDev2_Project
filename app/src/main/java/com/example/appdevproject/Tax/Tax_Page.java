package com.example.appdevproject.Tax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appdevproject.Investment.Models.Totals_Save;
import com.example.appdevproject.R;
import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Tax.Adapters.Tax_InvestRecyAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Tax_Page extends AppCompatActivity {

    private TextView labourFor, investFor, govWants;
    private RecyclerView labour_recycle, invest_recycle;
    private FloatingActionButton fab;
    private ProjectDb myDb;

    private RecyclerView.LayoutManager layoutManager;

    private Tax_InvestRecyAdapter recyAdapter;


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


    //top recycler



    //bottom recycler
        makeInvestedAdapter();

//        labourFor.setText("eqeqe");
//                String.format("I gained: %.2f",recyAdapter.getMyNetGain()));


        govWants.setText(String.format("Gov's cut: $%.2f",recyAdapter.getNetTax()));





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

    public void makeInvestedAdapter(){
          List<Totals_Save> myTaxableItems= myDb.totals_readTotal(getForeighnkey());


        layoutManager= new LinearLayoutManager(this);
        invest_recycle.setLayoutManager(layoutManager);

        recyAdapter= new Tax_InvestRecyAdapter();
        recyAdapter.setMyItems(myTaxableItems);
        invest_recycle.setAdapter(recyAdapter);
    }

    public int getForeighnkey() {
        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= myDb.getUserById(s.getString("username",""));
        return foreignKey;
    }


}