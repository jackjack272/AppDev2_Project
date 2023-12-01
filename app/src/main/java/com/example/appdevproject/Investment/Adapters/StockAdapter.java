package com.example.appdevproject.Investment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Models.Invest_Stock;
import com.example.appdevproject.Investment.Models.Totals_Save;
import com.example.appdevproject.R;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.InternalClass> {
    Context context;
    ProjectDb projectDb;
    List<Invest_Stock> myStock;


    public StockAdapter (Context context){
        this.context= context;
        projectDb= new ProjectDb(context);

    }

    public class InternalClass extends RecyclerView.ViewHolder{

        TextView ticker, pricePerEarning, monthlyPay;
        public InternalClass(@NonNull View itemView) {
            super(itemView);


        }

    }

    @NonNull
    @Override
    public StockAdapter.InternalClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invest_card_stock_adapter, parent, false);
        InternalClass internalClass= new InternalClass(view);
        return  internalClass;
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.InternalClass holder, int position) {

    }

    @Override
    public int getItemCount() {
        return myStock.size();
    }

    public List<Invest_Stock> getMyStock() {
        return myStock;
    }

    public void setMyStock(List<Invest_Stock> myStock) {
        this.myStock = myStock;
    }
}




