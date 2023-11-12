package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.R;

public class Invest_Edit extends AppCompatActivity {
    TextView heading;
    EditText name, amountBorrowed,interestRate,compoundsPerYear,numMonths;
    Button edit, delete;

    ProjectDb projectDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_invest);

        makeAssociateions();



        Intent x=getIntent();
        Bundle bundle= x.getExtras();
        int id = bundle.getInt("category");

        Invest_Debt debt= projectDb.debt_readOne(id);
        setValues(debt);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Invest_Debt xx= getUpdatedValues();

                if(xx==null){
                    return;
                }
                projectDb.debt_updateOne(id,xx);
                Toast.makeText(Invest_Edit.this, "Item "+xx.getDebtName()+" is updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectDb.debt_deleteOne(id);
                Toast.makeText(Invest_Edit.this, "Deleted item", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void makeAssociateions(){
        heading=findViewById(R.id.invest_edit_heading);
        name=findViewById(R.id.invest_edit_name);
        amountBorrowed=findViewById(R.id.invest_edit_amountBorrowed);
        interestRate=findViewById(R.id.invest_edit_interestRate);
        compoundsPerYear=findViewById(R.id.invest_edit_compounds);
        numMonths=findViewById(R.id.invest_edit_months);

        edit =findViewById(R.id.invest_edit_edit);
        delete=findViewById(R.id.invest_edit_delete);

        projectDb=new ProjectDb(Invest_Edit.this);

    }

    public int getForeighnkey() {
        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= projectDb.getUserById(s.getString("username",""));
        return foreignKey;
    }

    public void setValues(Invest_Debt xx){
        name.setHint(xx.getDebtName());
        amountBorrowed.setHint(String.valueOf( xx.getAmountBorred() ));
        interestRate.setHint(String.valueOf( xx.getAnnualCompoundRate()));
        compoundsPerYear.setHint(xx.getCompoundsPerYear());
        numMonths.setHint(xx.getLoanTermInMonths());

    }

    public Invest_Debt getUpdatedValues(){
        String name;
        Double amountBorrowed, interestRate;
        Integer compoundsPerYear,loanTerm;

        Boolean badValues=false;

        name=this.name.getText().toString();
        if(name.equals("")){
            badValues=true;
            this.name.setHint("enter a name");
        }


        amountBorrowed=Double.parseDouble(this.amountBorrowed.getText().toString());
        if(amountBorrowed<=0){
            badValues=true;
            this.amountBorrowed.setHint("enter a number");
        }

        interestRate= Double.parseDouble( this.interestRate.getText().toString());
        if(interestRate <=0){
            badValues=true;
            this.amountBorrowed.setHint("enter a number");
        }

        compoundsPerYear=Integer.parseInt( this.compoundsPerYear.getText().toString());
        if(compoundsPerYear<=0){
            badValues=true;
            this.amountBorrowed.setHint("enter a number");
        }


        loanTerm = Integer.parseInt(this.numMonths.getText().toString());
        if(loanTerm <=0 ){
            badValues=true;
            this.numMonths.setHint("enter a number");
        }



        if(!badValues){
            return new Invest_Debt(name, amountBorrowed, interestRate, compoundsPerYear,loanTerm );
        }
        return null;
    }





}