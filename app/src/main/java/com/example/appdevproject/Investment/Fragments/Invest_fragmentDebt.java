package com.example.appdevproject.Investment.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdevproject.Investment.Money.Invest_Debt;
import com.example.appdevproject.R;
import com.example.appdevproject.Utility.ProjectDb;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Invest_fragmentDebt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Invest_fragmentDebt extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Invest_fragmentDebt() {
        // Required empty public constructor
    }

    public static Invest_fragmentDebt newInstance(String param1, String param2) {
        Invest_fragmentDebt fragment = new Invest_fragmentDebt();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invest__debt, container, false);
    }



//coding area.

    EditText debtName, amountBorrowed, interestRate, compoundsPerYear, monthsOnLoan;
    Button saveBtn;
    ProjectDb projectDb;

    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState){
        makeAssocications();

        //save a debt to the db
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Invest_Debt newDebt= createNewDebt();
                if(newDebt ==null){
                    return;
                }
                newDebt.setForeinKey(getUserId());
                projectDb.debt_makeOne(newDebt);
                Toast.makeText(getContext(), "added a new debt", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void makeAssocications(){
        debtName= getView().findViewById(R.id.invest_debt_Name);
        amountBorrowed= getView().findViewById(R.id.invest_debt_getAmountBorrowed);
        interestRate= getView().findViewById(R.id.invest_debt_getInterestRate);
        compoundsPerYear=getView().findViewById(R.id.invest_debt_getCompoundsPerYear);
        monthsOnLoan=getView().findViewById(R.id.invest_debt_getTimeInMonths);

        saveBtn=getView().findViewById(R.id.invest_debt_btn);

        projectDb= new ProjectDb(getContext());
    }

    public Invest_Debt createNewDebt(){
        Double amountBorrowed, interestRate;
        Integer compounds, loanterm;
        String name= debtName.getText().toString();

        Boolean badValues=false;
        try{
            amountBorrowed= Double.parseDouble(this.amountBorrowed.getText().toString() );
        }catch (Exception e){
            this.amountBorrowed.setText("");
            this.amountBorrowed.setHint("bad amount borrowed");
            badValues=true;
            amountBorrowed=0.0;
        }

        try{
            interestRate= Double.parseDouble( this.interestRate.getText().toString());
        }catch (Exception e){
            this.interestRate.setText("");
            this.interestRate.setHint("bad interest rate");
            badValues=true;
            interestRate=0.0;
        }

        try{
            compounds= Integer.parseInt( this.compoundsPerYear.getText().toString());
        }catch (Exception e){
            this.compoundsPerYear.setText("");
            this.compoundsPerYear.setHint("bad compounds");
            badValues=true;
            compounds=0;
        }

        try{
            loanterm= Integer.parseInt(this.monthsOnLoan.getText().toString());
        }catch (Exception e){
            this.monthsOnLoan.setHint("");
            this.monthsOnLoan.setHint("bad loan term");
            badValues=true;
            loanterm=0;
        }


        if(badValues){
            Toast.makeText(getContext(), "Please correct the indicated values", Toast.LENGTH_SHORT).show();
        }else {
            if(amountBorrowed >0 && interestRate> 0 &&
                    compounds>0 && loanterm >0){
                return new Invest_Debt(name,amountBorrowed, interestRate, compounds, loanterm);
            }
        }
        return  null;
    }

    public Integer getUserId(){
        SharedPreferences sharedPreferences= requireContext()
                .getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String userName=sharedPreferences.getString("username","");
        Integer id= projectDb.getUserById(userName);
        return id;
    }

}