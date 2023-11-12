package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appdevproject.DataBase.Interfaces.Totals;
import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Adapters.BondsAdapter;
import com.example.appdevproject.Investment.Adapters.DebtAdapter;
import com.example.appdevproject.Investment.Adapters.StockAdapter;
import com.example.appdevproject.Investment.Models.Interfaces.Debt;
import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.R;

import java.util.List;

public class Invest_ShowClickedCategory extends AppCompatActivity {
    TextView heading;
    RecyclerView recyclerView;
    BondsAdapter bondsAdapter;
    DebtAdapter debtAdapter;
    StockAdapter stockAdapter;
    RecyclerView.LayoutManager layoutManager;


//    Adapters


    ProjectDb myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_show_clicked_category);

        makeAssocications();

        String str="See all your ";

        int choice=getCategory();
        switch (choice){
            case 0:
                //SHOW ALL Bonds.
                str+="Bonds";
                break;
            case 1:
                //show all Debt
                str+="Debt";
                break;
            default:
                //show all stock.
                str+="Stock";
                break;
        }

        makeAdapter(choice);

        heading.setText(String.valueOf(str));

    }

    public void makeAdapter(int choice){
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        int foreignKey=getForeighnkey();

        if(choice==0) {
            bondsAdapter = new BondsAdapter(Invest_ShowClickedCategory.this);
            List<Invest_Debt> myBonds= myDb.debt_readBonds(foreignKey);
            bondsAdapter.setItems(myBonds);
            recyclerView.setAdapter(bondsAdapter);
        }
        else if(choice==1){
            debtAdapter=new DebtAdapter(Invest_ShowClickedCategory.this);
            recyclerView.setAdapter(debtAdapter);

        }else{
            stockAdapter=new StockAdapter(Invest_ShowClickedCategory.this);

            recyclerView.setAdapter(stockAdapter);
        }


    }
    public int getForeighnkey() {
        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= myDb.getUserById(s.getString("username",""));
        return foreignKey;
    }

    public Integer getCategory(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        return bundle.getInt("category",0);
    }



    public void makeAssocications(){
        heading= findViewById(R.id.invest_choice_display);
        recyclerView= findViewById(R.id.invest_choice_card_recycleview);

        myDb=new ProjectDb(Invest_ShowClickedCategory.this);

    }

}