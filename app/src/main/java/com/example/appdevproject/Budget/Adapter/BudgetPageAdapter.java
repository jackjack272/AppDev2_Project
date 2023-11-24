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
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class BudgetPageAdapter extends RecyclerView.Adapter<BudgetPageAdapter.BudgetViewHolder> {
    private List<Item> myItems;

    public BudgetPageAdapter(List<Item> myItems) {
        this.myItems = myItems;
    }

    public List<Item> getMyItems() {
        return myItems;
    }

    public void setMyItems(List<Item> myItems) {
        this.myItems = myItems;
    }

    @NonNull
    @Override
    public BudgetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.budget_item_layout,parent,false);
        return new BudgetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetViewHolder holder, int position) {
        holder.item_name.setText(myItems.get(position).getNameOfItem());
        holder.item_amount.setText(String.valueOf(myItems.get(position).getPriceOfItem()));
        holder.item_value_of_contract.setText(String.valueOf(myItems.get(position).getContractLength()));
        //holder.budget_yearly_renewal.setText(String.valueOf(myItems.get(position).getYearlyRenewalFee()));
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    public class BudgetViewHolder extends RecyclerView.ViewHolder {
        TextView item_name,item_amount,item_value_of_contract,budget_yearly_renewal;
        public BudgetViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_amount = itemView.findViewById(R.id.item_amount);
            item_value_of_contract = itemView.findViewById(R.id.item_value_of_contract);
            //budget_yearly_renewal = itemView.findViewById(R.id.budget_yearly_renewal);
        }
    }
}
