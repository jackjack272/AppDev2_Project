package com.example.appdevproject.Tax.Adapters;

import static com.example.appdevproject.DataBase.Interfaces.Totals.TOTAL_BOND_PK;
import static com.example.appdevproject.DataBase.Interfaces.Totals.TOTAL_DEBT_PK;
import static com.example.appdevproject.DataBase.Interfaces.Totals.TOTAL_STOCK_PK;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.Investment.Models.Totals_Save;
import com.example.appdevproject.R;
import com.example.appdevproject.Tax.Tax_Calculator;

import java.util.List;

public class Tax_InvestRecyAdapter extends RecyclerView.Adapter<Tax_InvestRecyAdapter.InternalClass_tax> {
    List<Totals_Save> myItems; //bonds, stocks dividends

    private Double myNetGain,netTax;

    public Tax_InvestRecyAdapter(){
        myNetGain=0.0;
        netTax=0.0;
    }

    @NonNull
    @Override
    public InternalClass_tax onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tax_invest_card,null,false);

        InternalClass_tax cc= new InternalClass_tax(v);
        return cc;
    }

    @Override
    public void onBindViewHolder(@NonNull InternalClass_tax holder, int position) {

            Double taxConsequence= getTaxConsequence(position);
            Double netGain=myItems.get(position).getYearlyInterestCharge()- taxConsequence;
            holder.name.setText(
                    String.format("name: %s", myItems.get(position).getName() ));
            holder.interest.setText(
                    String.format("interest: $%.2f", myItems.get(position).getYearlyInterestCharge()));
            holder.taxConsequence.setText(
                    String.format("taxed: $%.2f ",taxConsequence ));
            holder.netGain.setText(
                    String.format("gain: $%.2f",netGain));


            this.myNetGain+=netGain;
            this.netTax+=taxConsequence;

    }

    public double getTaxConsequence(int position){
        double taxConsequnece=0.0;
        switch (position){
            case TOTAL_BOND_PK:
                taxConsequnece= Tax_Calculator.bondTaxConsequence();
                break;
            case TOTAL_DEBT_PK:
                taxConsequnece= Tax_Calculator.bondTaxConsequence();
                break;
            case TOTAL_STOCK_PK:
                taxConsequnece= Tax_Calculator.stockTaxConsequence();
                break;
        }
        return taxConsequnece;
    }

    @Override
    public int getItemCount() {
//        return myItems.size()+1; // i want to the first card be the headings
        return myItems.size(); // i want to the first card be the headings
    }

    public class InternalClass_tax extends RecyclerView.ViewHolder{
        TextView name, interest, taxConsequence,netGain;
        public InternalClass_tax(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.tax_card_name);
            interest= itemView.findViewById(R.id.tax_card_interest);
            taxConsequence= itemView.findViewById(R.id.tax_card_taxConsequence);
            netGain= itemView.findViewById(R.id.tax_card_netGain);
        }
    }

    public void setMyItems(List<Totals_Save> myItems) {
        this.myItems = myItems;
    }



    public Double getMyNetGain() {
        return myNetGain;
    }

    public Double getNetTax() {
        return netTax;
    }
}