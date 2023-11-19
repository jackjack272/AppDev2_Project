package com.example.appdevproject.Investment.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.Investment.Invest_Edit;
import com.example.appdevproject.R;

import java.util.List;

public class BondsAdapter extends RecyclerView.Adapter<BondsAdapter.InternalClass> {
    Context context;

    List<Invest_Debt> myBonds;

    ProjectDb db;

    public BondsAdapter(Context context) {
        this.context = context;
        db=new ProjectDb(context);

    }

    @NonNull
    @Override
    public BondsAdapter.InternalClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invest_card_bonds, parent, false);

        InternalClass internalClass = new InternalClass(v);
        return internalClass;

    }

    @Override
    public void onBindViewHolder(@NonNull BondsAdapter.InternalClass holder, int position) {
        holder.heading.setText(myBonds.get(position).getDebtName());

        holder.amountBorrowed.setText(String.format("$ %.2f",  myBonds.get(position).getAmountBorred()));
        holder.effectiveInterestRate.setText(String.format("%.2f %%",  myBonds.get(position).getEffectiveInterestRate()));
        holder.payPerPeriod.setText(String.format("$ %.2f",  myBonds.get(position).paymentPerCompound()));
        holder.valueAtMaturity.setText(String.format("$ %.2f",  myBonds.get(position).valueAtMaturity()));


    }

    @Override
    public int getItemCount() {
        return myBonds.size();
    }


    public class InternalClass extends RecyclerView.ViewHolder {
        TextView heading, amountBorrowed, effectiveInterestRate,payPerPeriod,valueAtMaturity;
        Button edit, delete;


        public InternalClass(@NonNull View itemView) {
            super(itemView);
            //fields to fill

            heading=itemView.findViewById(R.id.invest_bond_card_heading);
            amountBorrowed=itemView.findViewById(R.id.invest_bond_card_amountBorrowed);

            effectiveInterestRate= itemView.findViewById(R.id.invest_bond_card_effectiveInterest);
            payPerPeriod= itemView.findViewById(R.id.invest_bond_card_payPerPeriod);
            valueAtMaturity=itemView.findViewById(R.id.invest_bond_card_valueAtMaturity);

            edit=itemView.findViewById(R.id.invest_bond_card_edit);
            delete=itemView.findViewById(R.id.invest_bond_card_delete);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion = getAdapterPosition();

                    Intent intent= new Intent(v.getContext(), Invest_Edit.class);
                    Bundle bundle= new Bundle();

                        bundle.putInt("id",myBonds.get(postion).getId());
                        bundle.putString("name", myBonds.get(postion).getDebtName());
                        bundle.putDouble("borrowed",myBonds.get(postion).getAmountBorred() );
                        bundle.putDouble("interest",myBonds.get(postion).getInterestRate() );
                        bundle.putDouble("year", myBonds.get(postion).getCompoundsPerYear());
                        bundle.putDouble("months",myBonds.get(postion).getLoanTermInMonths() );

                        if(myBonds.get(postion).getIsDebt()){
                            bundle.putInt("category",0);
                        }else{
                            bundle.putInt("category",1);
                        }

                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);

                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion = getAdapterPosition();

                    int id= myBonds.get(postion).getId();
                    db.debt_deleteOne(id);
                    edit.setVisibility(View.INVISIBLE);

                    Toast.makeText(amountBorrowed.getContext(), "Deleted item", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }


    //show all items.
    public void setItems(List<Invest_Debt> bonds){
        this.myBonds =bonds;
    }

}
