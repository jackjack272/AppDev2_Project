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

import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.Investment.Models.Totals_Save;
import com.example.appdevproject.Pages.Invest_Edit;
import com.example.appdevproject.Pages.Invest_ShowClickedCategory;
import com.example.appdevproject.R;

import java.util.List;

public class BondsAdapter extends RecyclerView.Adapter<BondsAdapter.InternalClass> {
    Context context;

    List<Invest_Debt> myBonds;


    public BondsAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public BondsAdapter.InternalClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invest_card_loan, parent, false);

        InternalClass internalClass = new InternalClass(v);
        return internalClass;

    }

    @Override
    public void onBindViewHolder(@NonNull BondsAdapter.InternalClass holder, int position) {
        holder.heading.setText(myBonds.get(position).getDebtName());

        holder.loanAmountLeft.setText(String.format("%.2f",myBonds.get(position).getMonthlyInterest() ));
        holder.loanMonthlyInterest.setText(String.format("%.2f", myBonds.get(position).getMonthlyInterest()));
        holder.marketValue.setText(String.format("%.2f", myBonds.get(position).getNewMarketValue()));
//                myBonds.get(position).getNewMarketValue()));

    }

    @Override
    public int getItemCount() {
        return myBonds.size();
    }


    public class InternalClass extends RecyclerView.ViewHolder {
        TextView loanName, loanMonthlyInterest, loanAmountLeft, marketValue;

        TextView totalAmount_title, monthlyInterest, newMarketValue;

        TextView heading;

        public InternalClass(@NonNull View itemView) {
            super(itemView);
            //fields to fill
            heading = itemView.findViewById(R.id.invest_card_name);

            loanName = itemView.findViewById(R.id.invest_card_name);
            marketValue = itemView.findViewById(R.id.invest_card_marketValue);
            loanMonthlyInterest = itemView.findViewById(R.id.invest_card_monthylInterest);
            loanAmountLeft = itemView.findViewById(R.id.invest_card_amountLeftOver);

            //title
            totalAmount_title = itemView.findViewById(R.id.invest_card_marketValueTitle);
            monthlyInterest = itemView.findViewById(R.id.invest_card_amountLeftOverTitle);
            newMarketValue = itemView.findViewById(R.id.invest_card_MonthlyInterstTitle);

            totalAmount_title.setText("Toatl invested");
            monthlyInterest.setText("avg monthly interest");
            newMarketValue.setText("new market value");



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion = getAdapterPosition();

                    Intent intent= new Intent(v.getContext(), Invest_Edit.class);
                    Bundle bundle= new Bundle();
                    bundle.putInt("category",myBonds.get(postion).getId());

                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }
            });


        }
    }


//crud

    //show all items.
    public void setItems(List<Invest_Debt> bonds){
        this.myBonds =bonds;
    }





}
