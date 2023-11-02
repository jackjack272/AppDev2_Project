package com.example.appdevproject.Tax;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appdevproject.Budget.InterFaces.Budget_onDialogCloseListener;
import com.example.appdevproject.R;
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

        if(activity instanceof Budget_onDialogCloseListener){
            ((Budget_onDialogCloseListener)activity).onDialogClose(dialogInterface);
        }
    }
// ---




    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){

    }




}
