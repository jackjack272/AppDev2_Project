package com.example.appdevproject.Debt_Repayment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Debt_Repayment.Adapters.Debt_Adapter;
import com.example.appdevproject.Debt_Repayment.Adapters.RepaymentScheduale_Adapter;
import com.example.appdevproject.Investment.Models.Interfaces.Debt;
import com.example.appdevproject.R;

public class Debt_Repayment extends AppCompatActivity {
//my buttons
    private Button size, interes;
    private RecyclerView debts,repayScheduale;
    private EditText overMinRepay;
    private TextView minRepay, lifeTimeSave;



    ProjectDb projectDb;

    Debt_Adapter debt_adapter;
    RepaymentScheduale_Adapter scheduale_adapter;

    RecyclerView.LayoutManager topLayout, bottomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loans);

        makeAssociations();

        //make a debt adapter
        makeAdapter();



    }

    public void makeAdapter(){
    //this is the botto adapter
            //this will holds repayment schedual cards.... maybe max of 10 if 300 cards then display 300/10= or everny 30th card.

        bottomLayout=new LinearLayoutManager(this);
        repayScheduale.setLayoutManager(bottomLayout);

        scheduale_adapter = new RepaymentScheduale_Adapter();
        repayScheduale.setAdapter(scheduale_adapter);

//this is the top adapter- shows the current debts. And orders them
        topLayout = new LinearLayoutManager(this);
        debts.setLayoutManager(topLayout);

        debt_adapter = new Debt_Adapter(Debt_Repayment.this);
        debts.setAdapter(debt_adapter);
    }

    public void makeAssociations(){
        //buttons to order the data in display
        size= findViewById(R.id.debt_repay_size);
        interes= findViewById(R.id.debt_repay_interest);

        //recycler views
        debts=findViewById(R.id.debt_repay_debts);
        repayScheduale=findViewById(R.id.debt_repay_schedule);

        //edit text
        overMinRepay=findViewById(R.id.debt_repay_extra);

        //textview //outputfields
        minRepay= findViewById(R.id.debt_repay_tellMinRepay);
        lifeTimeSave=findViewById(R.id.debt_repay_lifeTimeSave);

        projectDb=new ProjectDb(Debt_Repayment.this);
    }


}