package com.example.appdevproject.Investment.Fab;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.appdevproject.Investment.Fragments.Invest_fragmentDebt;
import com.example.appdevproject.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Invest_AddNewInvest extends BottomSheetDialogFragment {

    Button debt, stock, bond;
    View output;
    //area to make code work.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveBundle){
        makeAssocications();

        // start a fragment.
        debt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start fragment for

                Toast.makeText(getContext(), "Debt", Toast.LENGTH_SHORT).show();

//            FragmentTransaction transaction = requireActivity().
//                        getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.invest_fab_fragment,
//                        new Invest_fragmentDebt());
//                transaction.addToBackStack(null);
//                transaction.commit();

            }
        });

        stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "stock", Toast.LENGTH_SHORT).show();
            }
        });

        bond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "bond", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void makeAssocications(){
        debt= getView().findViewById(R.id.invest_add_debt);
        stock= getView().findViewById(R.id.invest_add_stock);
        bond= getView().findViewById(R.id.invest_add_bond);
        output= getView().findViewById(R.id.invest_fab_fragment);

    }




//boiler plate
    public static final String TAG="AddNewItemToInvest";
    //boiler plate
    public static Invest_AddNewInvest newInstace(){return new Invest_AddNewInvest();}
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle saveInstanceState){
        View v= inflater.inflate(R.layout.invest_choicecontrol , container, false);
        return v;
    }
    public void onDismiss(@NonNull DialogInterface dialogInterface){
        super.onDismiss(dialogInterface);
        Activity activity= getActivity();
        if(activity instanceof Invest_onDialogCloseListener){
            ((Invest_onDialogCloseListener)activity).onDialogClose(dialogInterface);
        }
    }


}
