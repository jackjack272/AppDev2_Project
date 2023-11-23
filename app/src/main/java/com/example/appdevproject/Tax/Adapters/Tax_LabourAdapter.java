package com.example.appdevproject.Tax.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.R;
import com.example.appdevproject.Tax.Models.Tax_Income;
import com.example.appdevproject.Tax.Tax_Calculator;

import java.util.List;

public class Tax_LabourAdapter extends RecyclerView.Adapter<Tax_LabourAdapter.InternalClass_labour> {

    List<Tax_Income> myItems;

    public class InternalClass_labour extends RecyclerView.ViewHolder{
        TextView name, yealyWage, income_taxAmount,yearlyNet;
        public InternalClass_labour(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.tax_card_name);
            yealyWage= itemView.findViewById(R.id.tax_card_interest);
            income_taxAmount= itemView.findViewById(R.id.tax_card_taxConsequence);
            yearlyNet= itemView.findViewById(R.id.tax_card_netGain);
        }
    }



    @NonNull
    @Override
    public Tax_LabourAdapter.InternalClass_labour onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tax_invest_card,null,false);

        InternalClass_labour labour= new InternalClass_labour(v);
        return labour;
    }

    @Override
    public void onBindViewHolder(@NonNull Tax_LabourAdapter.InternalClass_labour holder, int position) {

        Double yearsIncome= myItems.get(position).getYearlyIncome();
        Double taxBurde= Tax_Calculator.incomeTaxConsequence(yearsIncome);


        holder.name.setText(myItems.get(position).getJobTitle());

        holder.yealyWage.setText(
                String.format("interest: $%.2f",yearsIncome ));

        holder.income_taxAmount.setText(
                String.format("taxed: $%.2f", taxBurde));

        holder.yearlyNet.setText(
                String.format("gain: $%.2f",yearsIncome-taxBurde));

    }

    @Override
    public int getItemCount() {
        return myItems.size();
//    return 5;
    }


    public void setMyItems(List<Tax_Income> myItems) {
        this.myItems = myItems;
    }


}
