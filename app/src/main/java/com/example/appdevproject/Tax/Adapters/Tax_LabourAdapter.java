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

    Double totalLabourTax,yearlyIncome;



    public Tax_LabourAdapter(){
        this.totalLabourTax=0.0;
        this.yearlyIncome=0.0;
    }


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

        Double local_yearsIncome= myItems.get(position).getYearlyIncome();
        Double taxBurde= Tax_Calculator.incomeTaxConsequence(local_yearsIncome);


        this.totalLabourTax+=taxBurde;
        this.yearlyIncome+=local_yearsIncome;

        holder.name.setText(myItems.get(position).getJobTitle());

        holder.yealyWage.setText(
                String.format("Yearly Gross: $%.2f",local_yearsIncome ));

        holder.income_taxAmount.setText(
                String.format("Taxed: $%.2f", taxBurde));

        holder.yearlyNet.setText(
                String.format("Yearly Net: $%.2f",local_yearsIncome-taxBurde));


    }

    @Override
    public int getItemCount() {
        return myItems.size();
//    return 5;
    }


    public void setMyItems(List<Tax_Income> myItems) {
        this.myItems = myItems;
    }

    public List<Tax_Income> getMyItems() {
        return myItems;
    }

    public Double getTotalLabourTax() {
        return totalLabourTax;
    }

    public void setTotalLabourTax(Double totalLabourTax) {
        this.totalLabourTax = totalLabourTax;
    }


    public Double getYearlyIncome() {
        return this.yearlyIncome;
    }

    public void setYearlyIncome(Double yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
    }
}



