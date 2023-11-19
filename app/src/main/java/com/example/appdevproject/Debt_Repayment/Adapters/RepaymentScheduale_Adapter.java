package com.example.appdevproject.Debt_Repayment.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.Investment.Adapters.BondsAdapter;
import com.example.appdevproject.R;

public class RepaymentScheduale_Adapter extends RecyclerView.Adapter<RepaymentScheduale_Adapter.Internalclass> {


    public class Internalclass extends RecyclerView.ViewHolder{




        public Internalclass(@NonNull View itemView) {
            super(itemView);
        }
    }


    @NonNull
    @Override
    public RepaymentScheduale_Adapter.Internalclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.debtrepay_card,parent,false);

        RepaymentScheduale_Adapter.Internalclass myClass= new RepaymentScheduale_Adapter.Internalclass(v);

        return myClass;

    }

    @Override
    public void onBindViewHolder(@NonNull RepaymentScheduale_Adapter.Internalclass holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 9;
    }
}
