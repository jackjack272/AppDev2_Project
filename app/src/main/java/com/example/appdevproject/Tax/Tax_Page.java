package com.example.appdevproject.Tax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appdevproject.R;
import com.example.appdevproject.DataBase.ProjectDb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Tax_Page extends AppCompatActivity {

    private TextView labourFor, investFor, govWants;
    private RecyclerView labour_recycle, invest_recycle;
    private FloatingActionButton fab;
    private ProjectDb myDb;


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


    }

    private void makeAssocications(){
        labourFor= findViewById(R.id.inc_labour);
        investFor= findViewById(R.id.inc_invest);
        govWants= findViewById(R.id.inc_govWants);

        labour_recycle= findViewById(R.id.inc_labourRecycle);
        invest_recycle=findViewById(R.id.inc_investRecycle);

        myDb= new ProjectDb(Tax_Page.this);
        fab= findViewById(R.id.inc_fab);
    }


}