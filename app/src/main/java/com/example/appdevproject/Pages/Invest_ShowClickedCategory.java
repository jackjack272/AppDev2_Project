package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Adapters.BondsAdapter;
import com.example.appdevproject.Investment.Adapters.StockAdapter;
import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class Invest_ShowClickedCategory extends AppCompatActivity {
    TextView heading;
    RecyclerView recyclerView;
    BondsAdapter bondsAdapter;
    StockAdapter stockAdapter;
    RecyclerView.LayoutManager layoutManager;
//    Adapters


    ProjectDb myDb;

    LineChart stackedChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_show_clicked_category);

        makeAssocications();

        int choice=showHeading();
        makeAdapter(choice);

        showChart();


    }


    public void showChart(){
        //https://www.youtube.com/watch?v=jTYi0Q7lLco&list=PLFh8wpMiEi89LcBupeftmAcgDKCeC24bJ&index=12

        ArrayList<Entry> dataVals= new ArrayList<>();

        dataVals.add(new Entry(0,20));
        dataVals.add(new Entry(1,30));
        dataVals.add(new Entry(2,40));
        dataVals.add(new Entry(3,50));

        LineDataSet lineDataSet= new LineDataSet(dataVals,"Bonds price ");
        ArrayList<ILineDataSet> dataSets= new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data= new LineData(dataSets);
        stackedChart.setData(data);
        stackedChart.invalidate();

    }



    public int showHeading(){
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

        heading.setText(str);
        return choice;
    }

    public void makeAdapter(int choice){
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        int foreignKey=getForeighnkey();

        if(choice==0 || choice==1) {
            bondsAdapter = new BondsAdapter(Invest_ShowClickedCategory.this);
            List<Invest_Debt> myItems;

            if(choice==0){
                myItems= myDb.debt_readBonds(foreignKey);
            }else{
                myItems= myDb.debt_readDebt(foreignKey);
            }
            bondsAdapter.setItems(myItems);
            recyclerView.setAdapter(bondsAdapter);
        }

        else {
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

        stackedChart=findViewById(R.id.bar_chart);

        myDb=new ProjectDb(Invest_ShowClickedCategory.this);

    }

}