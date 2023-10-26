package com.example.appdevproject.Budget.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.Budget.Item;
import com.example.appdevproject.InterFaces.Budget;
import com.example.appdevproject.Pages.BudgetPage;
import com.example.appdevproject.R;
import com.example.appdevproject.Utility.ProjectDb;

import java.util.List;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.InternalClass> {

    private List<Item> myItems;// list of the items to be displayed
    private BudgetPage budgetPage;// linked to budget page
    private ProjectDb myDb;


    //make a constructor, gets Activity and db pointers
    public BudgetAdapter(ProjectDb db, BudgetPage activity){
        budgetPage= activity;
        myDb= db;
    }

    //return this context.
    public Context getContext(){ return budgetPage;}


    //link budget page scroll view with budget .
    @NonNull
    @Override
    public BudgetAdapter.InternalClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.budget_item_layout, parent, false);
        return new InternalClass(v);
    }


    //populate the fields for one card with the content from db.
    @Override
    public void onBindViewHolder(@NonNull BudgetAdapter.InternalClass holder, int position) {
        Item item = myItems.get(position);

        holder.item_name.setText(item.getNameOfItem());
        holder.item_amount.setText(String.valueOf( item.getPriceOfItem()) );
        holder.item_renewalFee.setText(String.valueOf(item.getYearlyRenewalFee()));


        holder.item_leftOnContract.setText(String.valueOf(item.getFrequencyOfPurchase()));
            //left on contract would be (2 year contract)+renewal fee *monthly cost / .
            //idk its a formual of some sort that we can make up

    }

    // get reference to the card view fields.
    public static class InternalClass extends RecyclerView.ViewHolder {
        TextView item_name, item_amount , item_leftOnContract, item_renewalFee;
        public InternalClass(@NonNull View itemView) {
            super(itemView);
            item_name=itemView.findViewById(R.id.item_name);
            item_amount=itemView.findViewById(R.id.item_amount);
            item_leftOnContract= itemView.findViewById(R.id.item_value_of_contract);
            item_renewalFee= itemView.findViewById(R.id.budget_yearly_renewal);
        }
    }


// Crud on items.
    // be able to set the list from outside call
    public void setItems(int userID){
        //db.getAllItems(); // method not made yet.
        myItems= myDb.item_getAll(userID);
        notifyDataSetChanged();
    }
// Create many
    public void updateItem(int position){
        //db.itemEdit(pos,cv).
    }

// update
    public void deleteItem(int position){
        //db.itemDelete(pos);
    }
// delete

    @Override
    public int getItemCount() {
        return myItems.size();
    }




}