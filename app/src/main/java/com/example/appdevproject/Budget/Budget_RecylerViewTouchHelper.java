package com.example.appdevproject.Budget;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.Budget.Adapter.BudgetAdapter;

public class Budget_RecylerViewTouchHelper extends ItemTouchHelper.SimpleCallback {
    private BudgetAdapter budgetAdapter;

    public Budget_RecylerViewTouchHelper(BudgetAdapter adapter) {
        super(0, ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT);
        this.budgetAdapter= adapter;
    }



    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if( direction == ItemTouchHelper.RIGHT){

        // makes confirmation button to delete.
            AlertDialog.Builder builder= new AlertDialog.Builder(budgetAdapter.getContext());
            builder.setTitle("Delete Task");
            builder.setMessage("Are you sure?");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    budgetAdapter.deleteItem(position);
                }
            });
        //set a cancel option
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    budgetAdapter.notifyItemChanged(position);
                }
            });

        //create the alert
            AlertDialog dialog= builder.create();
            dialog.show();

        }else{
//            budgetAdapter.
        }

    }












    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

}
