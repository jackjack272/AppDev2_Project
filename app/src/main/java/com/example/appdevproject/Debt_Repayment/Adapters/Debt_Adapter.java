package com.example.appdevproject.Debt_Repayment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.R;

import java.util.List;

public class Debt_Adapter extends RecyclerView.Adapter<Debt_Adapter.InternalClass> {

    List<Invest_Debt> myDebts;
    Context context;

    public Debt_Adapter(Context context){
        this.context= context;
    }


    public class InternalClass extends RecyclerView.ViewHolder{
        public InternalClass(@NonNull View itemView) {

            super(itemView

            );
        }
    }

    @NonNull
    @Override
    public Debt_Adapter.InternalClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.debt_oneitem,parent,false);

        InternalClass internalClass=new InternalClass(v);

        return internalClass;
    }

    @Override
    public void onBindViewHolder(@NonNull Debt_Adapter.InternalClass holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 9;
    }


}
