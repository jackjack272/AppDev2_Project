package com.example.appdevproject.Investment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.Investment.Models.Totals_Save;
import com.example.appdevproject.R;

import java.util.List;

public class BondsAdapter extends RecyclerView.Adapter<BondsAdapter.InternalClass> {
    Context context;

    List<Totals_Save> totalsBaseList;


    public BondsAdapter(Context context) {
        this.context=context;

    }

    @NonNull
    @Override
    public BondsAdapter.InternalClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invest_card_bond_adapter,parent,false);

        InternalClass internalClass= new InternalClass(v);
        return internalClass;

    }

    @Override
    public void onBindViewHolder(@NonNull BondsAdapter.InternalClass holder, int position) {

    }

    @Override
    public int getItemCount() {
        return totalsBaseList.size();
    }


    public class InternalClass extends RecyclerView.ViewHolder{
        public InternalClass(@NonNull View itemView) {
            super(itemView);
        }
    }



}
