package com.example.appdevproject.Investment.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Models.Totals_Save;
import com.example.appdevproject.Investment.Invest_ShowClickedCategory;
import com.example.appdevproject.R;

import java.util.List;


public class SumsAdapter extends RecyclerView.Adapter<SumsAdapter.ViewHolder> {

    List<Totals_Save> totals;
    ProjectDb db;

    Context context;

    public SumsAdapter(Context context){
        db= new ProjectDb(context);
        this.context= context;
    }


    @NonNull
    @Override
    public SumsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invest_card_loan,parent,
                        false);

        ViewHolder viewHolder= new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SumsAdapter.ViewHolder holder, int position) {
        holder.loanName.setText(totals.get(position).getName() );

        holder.loanAmountLeft.setText(String.format("$ %.2f",totals.get(position).getTotalAmount()));
        holder.loanMonthlyInterest.setText(String.format("%.2f %%",totals.get(position).getYearlyGain() ));
        holder.marketValue.setText(String.format("$ %.2f ",totals.get(position).getYearlyInterestCharge() ));
    }

    @Override
    public int getItemCount() {return totals.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView loanName, loanMonthlyInterest, loanAmountLeft, marketValue;

        TextView totalAmount_title, monthlyInterest,newMarketValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //fields to fill
            loanName= itemView.findViewById(R.id.invest_card_name);
            marketValue=itemView.findViewById(R.id.invest_card_marketValue);
            loanMonthlyInterest= itemView.findViewById(R.id.invest_card_monthylInterest);
            loanAmountLeft=itemView.findViewById(R.id.invest_card_amountLeftOver);

            //titles can come here.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion = getAdapterPosition();

                    Intent intent= new Intent(v.getContext(),Invest_ShowClickedCategory.class);
                    Bundle bundle= new Bundle();
                    bundle.putInt("category",postion);

                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }
            });
        }


    }


//Crud.
    public void setSums(List<Totals_Save> myDebts){
        this.totals = myDebts;
    }


}



//            //title
//            totalAmount_title=itemView.findViewById(R.id.invest_card_marketValueTitle);
//            monthlyInterest=itemView.findViewById(R.id.invest_card_amountLeftOverTitle);
//            newMarketValue=itemView.findViewById(R.id.invest_card_MonthlyInterstTitle);
//
//
//            totalAmount_title.setText("Toatl invested");
//            monthlyInterest.setText("average monthly interest");
//            newMarketValue.setText("new market value");