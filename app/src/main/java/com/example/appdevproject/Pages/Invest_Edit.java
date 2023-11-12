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
        setContentView(R.layout.invest_edit_activity);

        makeAssociateions();

        adminPutvalues();


        Intent x=getIntent();
        Bundle bundle= x.getExtras();
        int id= bundle.getInt("id");


        setValues(new Invest_Debt(
                bundle.getString("name"),
                bundle.getDouble("borrowed"),
                bundle.getDouble("interest"),
                bundle.getInt("year"),
                bundle.getInt("months")
                ));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Invest_Debt xx= getUpdatedValues();

                if(xx==null){
                    return;
                }
                projectDb.debt_updateOne(id,xx);
                Toast.makeText(Invest_Edit.this, "Item "+xx.getDebtName()+" is updated", Toast.LENGTH_SHORT).show();


                Intent intent= new Intent( Invest_Edit.this, Invest_ShowClickedCategory.class);
                Bundle myBundle= new Bundle();
                myBundle.putInt("category", bundle.getInt("category"));

                intent.putExtras(myBundle);
                startActivity(intent);
                //need a bundle with a category
            }
        });


    }
    public void adminPutvalues(){
        heading.setText("3");
        name.setText("3");
        amountBorrowed.setText("3");
        interestRate.setText("3");
        compoundsPerYear.setText("3");
        numMonths.setText("3");
    }


    public void makeAssociateions(){
        heading=findViewById(R.id.invest_edit_heading);
        name=findViewById(R.id.invest_edit_name);
        amountBorrowed=findViewById(R.id.invest_edit_amountBorrowed);
        interestRate=findViewById(R.id.invest_edit_interestRate);
        compoundsPerYear=findViewById(R.id.invest_edit_compounds);
        numMonths=findViewById(R.id.invest_edit_months);

        edit =findViewById(R.id.invest_edit_edit);


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
        compoundsPerYear.setHint(String.valueOf( xx.getCompoundsPerYear()));
        numMonths.setHint(String.valueOf( xx.getLoanTermInMonths()));

    }

    public Invest_Debt getUpdatedValues(){
        String name;
        Double amountBorrowed=-1.0, interestRate=-1.0;
        Integer compoundsPerYear=-1,loanTerm=-1;

        Boolean badValues=false;


        name=this.name.getText().toString();
        if(name.equals("")){
            badValues=true;
            this.name.setHint("enter a name");
        }

        try{
            amountBorrowed=Double.parseDouble(this.amountBorrowed.getText().toString());
            if(amountBorrowed<=0){
                badValues=true;
                this.amountBorrowed.setHint("enter amount borrowed >1");

            }
        }catch (Exception e){badValues=true; this.amountBorrowed.setHint("enter amount borrowed");}

        try {
            interestRate= Double.parseDouble( this.interestRate.getText().toString());
            if(interestRate <=0){
                badValues=true;
                this.amountBorrowed.setHint("enter interest rate >1");
            }
        }catch (Exception e){badValues=true;this.interestRate.setHint("enter interest rate");}


        try {
            compoundsPerYear=Integer.parseInt( this.compoundsPerYear.getText().toString());
            if(compoundsPerYear<=0){
                badValues=true;
                this.amountBorrowed.setHint("enter a compounds per year >1");
            }

        }catch (Exception e){badValues=true;this.compoundsPerYear.setHint("enter a compounds per year");}


        try {
            loanTerm = Integer.parseInt(this.numMonths.getText().toString());
            if(loanTerm <=0 ){
                badValues=true;
                this.numMonths.setHint("enter a number of months on the bond>1");
            }

        }catch (Exception e){badValues=true;this.numMonths.setHint("enter a number of months on the bond");}


        if(!badValues){
            return new Invest_Debt(name, amountBorrowed, interestRate, compoundsPerYear,loanTerm );
        }
        return null;
    }





}