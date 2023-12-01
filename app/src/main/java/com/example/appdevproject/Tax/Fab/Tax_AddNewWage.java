package com.example.appdevproject.Tax.Fab;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appdevproject.Budget.InterFaces.Budget_onDialogCloseListener;
import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Fab.Invest_onDialogCloseListener;
import com.example.appdevproject.R;
import com.example.appdevproject.Tax.Models.Tax_Income;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Tax_AddNewWage extends BottomSheetDialogFragment {

    public static final String TAG= "Tax_AddNewWage";

    public static Tax_AddNewWage newInstace(){
        return new Tax_AddNewWage();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        View v= inflater.inflate(R.layout.tax_addnew_labour, container, false);
        return v;
    }

    public void onDismiss(@NonNull DialogInterface dialogInterface){
        super.onDismiss(dialogInterface);
        Activity activity= getActivity();
        if(activity instanceof Tax_onDialogCloseListener){
            ((Invest_onDialogCloseListener)activity).onDialogClose(dialogInterface);
        }
    }
// ---


    TextView title, errors;
    EditText jobTitle, hoursWorked, incomeHourlyWage, bonuses;
    Button submit;

    ProjectDb projectDb;

    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){
        makeAssociations(view);

        admin_prepopulateForm();

        setHeading();
        //get the object, save the object.


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tax_Income oneJob=getObject();

                oneJob.setForeignKey(getForeighnkey(view));
                if (projectDb.income_saveOne(oneJob)){
                    Toast.makeText(view.getContext(), "Income Added", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public Tax_Income getObject(){
        String jobTitle;
        Double hoursWorked, incomeHourlyWage, bonuses;
        String errs="";

        jobTitle=this.jobTitle.getText().toString();
        if(jobTitle.equals("")){
            errs+="Job Title needs Correction\n";
        }

        hoursWorked=Double
                .parseDouble(this.hoursWorked.getText().toString()) ;
        if(hoursWorked<1){
            errs+="Wage needs Correction\n";
        }

        incomeHourlyWage= Double
                .parseDouble(this.incomeHourlyWage.getText().toString());
        if(incomeHourlyWage<1){
            errs+="Hourly Income needs Correction\n";

        }

        bonuses= Double.parseDouble(this.bonuses.getText().toString());
        if(bonuses <1){
            errs+="Bonuses needs Correction\n";
        }

        if(errs.equals("")){ // no errs.
            return new Tax_Income(jobTitle, incomeHourlyWage,hoursWorked,bonuses);
        }else{
            errors.setText(errs);
            return null;
        }
    }

    public void setHeading(){
        title.setText("Life Exchanged For Income.");
        title.setTextSize(24);
    }

    public void makeAssociations(View view){
//textview
        title=view.findViewById(R.id.tax_addnewCard_heading);
        errors=view.findViewById(R.id.tax_addnewCard_errors);

//Edit Text
        jobTitle=view.findViewById(R.id.tax_addnewCard_jobTitle);
        incomeHourlyWage=view.findViewById(R.id.tax_addnewCard_hourlyWage);
        hoursWorked=view.findViewById(R.id.tax_addnewCard_hoursWorked);
        bonuses=view.findViewById(R.id.tax_addnewCard_bonuses);

// buttons
        submit= view.findViewById(R.id.tax_addnewCard_submit);

//extra.
        projectDb=new ProjectDb(view.getContext());
    }


    public int getForeighnkey(View view ) {
        SharedPreferences s=view.getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= projectDb.getUserById(s.getString("username",""));
        return foreignKey;
    }


    public void admin_prepopulateForm(){

//        title.setText("RealEstate");
        errors.setText("");
        jobTitle.setText("Sales");
        hoursWorked.setText("45");
        incomeHourlyWage.setText("30");
        bonuses.setText("200");

    }












}