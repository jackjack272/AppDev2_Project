package com.example.appdevproject.Debt_Repayment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.DataBase.ProjectDb;
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
            TextView debtName, interestRate, interestPay, amount;

        public InternalClass(@NonNull View itemView) {
            super(itemView);

            debtName=itemView.findViewById(R.id.debt_oneitem_debtname);
            interestRate=itemView.findViewById(R.id.debt_oneitem_interest);
            interestPay=itemView.findViewById(R.id.debt_oneitem_interestPayment);
            amount=itemView.findViewById(R.id.debt_oneitem_totalBorrowed);
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
        holder.debtName.setText(myDebts.get(position).getDebtName());

        holder.interestRate.setText(
                String.format("%.2f" , myDebts.get(position).getEffectiveInterestRate())
        );

        holder.interestPay.setText(
                String.format("%.2f", myDebts.get(position).paymentPerCompound())
        );

        holder.amount.setText(String.valueOf(myDebts.get(position).getAmountBorred()));

    }

    @Override
    public int getItemCount() {
        return myDebts.size();
    }


    public void setItems(List<Invest_Debt> debt){
        this.myDebts= debt;
    }


}
