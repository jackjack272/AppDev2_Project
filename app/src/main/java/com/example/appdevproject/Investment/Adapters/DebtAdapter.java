package com.example.appdevproject.Investment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.Investment.Models.Totals_Save;
import com.example.appdevproject.R;

import java.util.List;

public class DebtAdapter extends RecyclerView.Adapter<DebtAdapter.InternalClass> {

    Context contetxt;

    List<Invest_Debt> myDebts;

    public DebtAdapter(Context contetxt) {
        this.contetxt=contetxt;
    }


    public class InternalClass extends RecyclerView.ViewHolder{
        public InternalClass(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public DebtAdapter.InternalClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invest_card_debt_adapter,parent, false);

        InternalClass internalClass= new InternalClass(v);
        return internalClass;
    }

    @Override
    public void onBindViewHolder(@NonNull DebtAdapter.InternalClass holder, int position) {


    }

    @Override
    public int getItemCount() {
        return myDebts.size();
    }


    public class InternalError extends RecyclerView.ViewHolder{

        public InternalError(@NonNull View itemView) {
            super(itemView);

        }
    }





}
