package com.example.appdevproject.Debt_Repayment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Debt_Repayment.Adapters.Debt_Adapter;
import com.example.appdevproject.Debt_Repayment.Adapters.RepaymentScheduale_Adapter;
import com.example.appdevproject.Debt_Repayment.Models.RepaySchedualeItem;
import com.example.appdevproject.Investment.Models.Interfaces.Debt;
import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.R;

import java.util.List;

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
        minRepay.setText("just interest: $0");

        //make it so when i enter a new payment it gets accounted for.



        //make a debt adapter
        int foreignKey=getForeighnkey();
        size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Invest_Debt> myDebts= projectDb.debt_readDebt(foreignKey); //default is size.
                makeAdapter();
                debt_adapter.setItems(myDebts);


                if(myDebts.size()==0){
                    return;
                }
                minRepay.setText(String.format("just interest: $%.2f",calcRepay(myDebts)));

                setBottomAdapterValues(myDebts);
            }
        });
        interes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Invest_Debt> myDebts= projectDb.debt_readDebt(foreignKey,1); //default is size.
                makeAdapter();
                debt_adapter.setItems(myDebts);

                if(myDebts.size()==0){
                    return;
                }
                minRepay.setText(String.format("just interest: $%.2f",calcRepay(myDebts)));

                setBottomAdapterValues(myDebts);
            }
        });

    }

    public void setBottomAdapterValues(List<Invest_Debt> myDebts){

        double contribution=calcRepay(myDebts);
        try{
            double overMin= Double.parseDouble( overMinRepay.getText().toString() );
            if (overMin >1){
                contribution+=overMin;
            }

        }catch (Exception e){}

        List<RepaySchedualeItem> repay= RepaySchedualeItem
                .getRepaymentScheduale(myDebts,
                        contribution //im the value that +$50 to make the repay, adjust me
                );

        //need to make this one.... ezz.... :,(
        lifeTimeSave.setText(
                String.format("Total Cost Of Debt: %.2f", repay.get( repay.size()-1).getTotalAmountRepay())
        );
        makeBottomAdapter();
        scheduale_adapter.setMyItems(repay);
    }

    public void makeAdapter(){
        topLayout = new LinearLayoutManager(this);
        debts.setLayoutManager(topLayout);

        debt_adapter = new Debt_Adapter(Debt_Repayment.this);
        debts.setAdapter(debt_adapter);

    }

    public void makeBottomAdapter(){
        //this is the botto adapter
        //this will holds repayment schedual cards.... maybe max of 10 if 300 cards then display 300/10= or everny 30th card.

        bottomLayout=new LinearLayoutManager(this);
        repayScheduale.setLayoutManager(bottomLayout);

        scheduale_adapter = new RepaymentScheduale_Adapter();
        repayScheduale.setAdapter(scheduale_adapter);
    }

    public double calcRepay(List<Invest_Debt> myDebts){
        double payment=0.0;
        for(Invest_Debt one:myDebts ){
            payment+= one.paymentPerCompound();
        }
        minRepay.setText(String.format("min repayment: $%.2f",payment));
        return payment;
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
        lifeTimeSave=findViewById(R.id.debt_repay_lifeTimeSave); //lifetime cost of debt.

        projectDb=new ProjectDb(Debt_Repayment.this);
    }

    public int getForeighnkey() {
        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= projectDb.getUserById(s.getString("username",""));
        return foreignKey;
    }

}