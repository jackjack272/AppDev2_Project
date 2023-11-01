package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.appdevproject.R;

public class TaxPage extends AppCompatActivity {

    private TextView labourFor, investFor, govWants;
    private RecyclerView labour_recycle, invest_recycle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        makeAssocications();



    }

    private void makeAssocications(){
        labourFor= findViewById(R.id.inc_labour);
        investFor= findViewById(R.id.inc_invest);
        govWants= findViewById(R.id.inc_govWants);

        labour_recycle= findViewById(R.id.inc_labourRecycle);
        invest_recycle=findViewById(R.id.inc_investRecycle);
    }


}