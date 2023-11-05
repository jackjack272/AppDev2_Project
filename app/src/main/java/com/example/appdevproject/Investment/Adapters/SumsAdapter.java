package com.example.appdevproject.Investment.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.Investment.Models.Sum;
import com.example.appdevproject.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class SumsAdapter extends RecyclerView.Adapter<SumsAdapter.ViewHolder> {

    List<Sum> sums;



    @NonNull
    @Override
    public SumsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invest_loan_cardview,parent, false);

        ViewHolder viewHolder= new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SumsAdapter.ViewHolder holder, int position) {

        holder.loanName.setText(sums.get(position).getName() );
        holder.loanAmountLeft.setText("0");
        holder.loanMonthlyInterest.setText("0");
    }

    @Override
    public int getItemCount() {return sums.size();}

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView loanName, loanMonthlyInterest, loanAmountLeft;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            loanName= itemView.findViewById(R.id.invest_card_name);
            loanMonthlyInterest= itemView.findViewById(R.id.invest_card_monthylInterest);
            loanAmountLeft=itemView.findViewById(R.id.invest_card_amountLeftOver);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion = getAdapterPosition();
                    Toast.makeText(loanName.getContext(), "taking you to 'stock' ", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }


//Crud.

    public void setSums(List<Sum> myDebts){
        this.sums= myDebts;
    }


}
