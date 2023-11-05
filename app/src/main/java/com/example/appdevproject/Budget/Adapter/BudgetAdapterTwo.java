package com.example.appdevproject.Budget.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.Budget.Model.Item;
import com.example.appdevproject.R;
import com.example.appdevproject.DataBase.ProjectDb;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class BudgetAdapterTwo extends RecyclerView.Adapter<BudgetAdapterTwo.Internal> {

    private ProjectDb db;
    private List<Item> myItems;

    public BudgetAdapterTwo(Context context){
        db=new ProjectDb(context);
    }


    @NonNull
    @Override
    public BudgetAdapterTwo.Internal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.budget_item_layout,parent, false);

        Internal internal = new Internal(v);
        return internal;
    }


    public static class Internal extends RecyclerView.ViewHolder {
        TextView item_name, item_amount , item_leftOnContract, item_renewalFee;
        public Internal(@NonNull View itemView) {
            super(itemView);
            item_name=itemView.findViewById(R.id.item_name);
            item_amount=itemView.findViewById(R.id.item_amount);
            item_leftOnContract= itemView.findViewById(R.id.item_value_of_contract);
            item_renewalFee= itemView.findViewById(R.id.budget_yearly_renewal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion= getAdapterPosition();
                    Snackbar.make(v, "click detected "+postion, Snackbar.LENGTH_SHORT)
                            .setAction("Action",null).show();
                }
            });
        }
    }




    @Override
    public void onBindViewHolder(@NonNull BudgetAdapterTwo.Internal holder, int position) {
        holder.item_name.setText(myItems.get(position).getNameOfItem());

        holder.item_amount.setText(String.valueOf( myItems.get(position).getPriceOfItem() ));
        holder.item_leftOnContract.setText(String.valueOf(myItems.get(position).getContractLength()));
        holder.item_renewalFee.setText(String.valueOf(myItems.get(position).getYearlyRenewalFee()));
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }



//Crud


//read all
    public void setItems(List<Item> myItems){
        this.myItems=myItems;
    }








}
