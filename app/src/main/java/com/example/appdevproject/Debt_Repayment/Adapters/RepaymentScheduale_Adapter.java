package com.example.appdevproject.Debt_Repayment.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdevproject.Debt_Repayment.Models.RepaySchedualeItem;
import com.example.appdevproject.Investment.Adapters.BondsAdapter;
import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.R;

import java.util.List;

public class RepaymentScheduale_Adapter extends RecyclerView.Adapter<RepaymentScheduale_Adapter.Internalclass> {

    List<RepaySchedualeItem> myItems;


    public class Internalclass extends RecyclerView.ViewHolder{
        TextView payNum, payAmount,payInterest, payPrince, newPrince;

        public Internalclass(@NonNull View itemView) {
            super(itemView);

            payNum=itemView.findViewById(R.id.debtrepay_card_payNum);
            payInterest=itemView.findViewById(R.id.debtrepay_card_interest);
            payPrince=itemView.findViewById(R.id.debtrepay_card_payPrince);
            payAmount=itemView.findViewById(R.id.debtrepay_card_payAmount);
            newPrince=itemView.findViewById(R.id.debtrepay_card_newPrnce);
        }
    }


    @NonNull
    @Override
    public RepaymentScheduale_Adapter.Internalclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.debtrepay_card,parent,false);

        RepaymentScheduale_Adapter.Internalclass myClass= new RepaymentScheduale_Adapter.Internalclass(v);

        return myClass;

    }

    @Override
    public void onBindViewHolder(@NonNull RepaymentScheduale_Adapter.Internalclass holder, int position) {
        holder.payNum.setText(String.format("payment# : %d ", myItems.get(position).getRepaymentNumber()+1 ));
        holder.newPrince.setText(String.format("new amount: %.2f", myItems.get(position).getNewPrice() ));


        holder.payInterest.setText(String.format("Interest: %.2f",   myItems.get(position).getToInteret() ));

        holder.payAmount.setText(String.format("Payment: %.2f", myItems.get(position).getPaymentAmount()  ));

        holder.payPrince.setText(String.format("actl: %.2f", myItems.get(position).getToPrinciple() ));
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }


    public void setMyItems(List<RepaySchedualeItem> myItems) {
        this.myItems = myItems;
    }

}
