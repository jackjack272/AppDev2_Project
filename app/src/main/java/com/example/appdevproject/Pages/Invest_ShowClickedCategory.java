package com.example.appdevproject.Pages;

import static com.example.appdevproject.DataBase.Interfaces.Totals.TOTAL_BOND_PK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Adapters.BondsAdapter;
import com.example.appdevproject.Investment.Adapters.StockAdapter;
import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.Investment.Models.Totals_Find;
import com.example.appdevproject.Investment.Models.Totals_Save;
import com.example.appdevproject.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

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

    BarChart stackedChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_show_clicked_category);

        makeAssocications();

        showHeading();
        makeAdapter(getCategory());

        updateTotals(); //i control the totals table

        showChart();
    }


    public void updateTotals(){
        int choice= getCategory();

        Totals_Save saveMe;
        switch (choice){
            case 0:

                Totals_Find find= new Totals_Find(Invest_ShowClickedCategory.this,getForeighnkey());

                saveMe=find.getBonds();
                saveMe.setForeignKey(getForeighnkey());

                if(! myDb.totals_empty()){
                    //exists then update

                    //need to save
                    saveMe.setId(TOTAL_BOND_PK); //DB TOTALS INTERFACE
                    myDb.totals_update(saveMe);
                }else{
                    //save
                    saveMe.setId(TOTAL_BOND_PK);
                    myDb.totals_saveOne(saveMe);

                }

                break;
            case 1:
//                saveMe.setId(1);


                break;
            case 2:
//                saveMe.setId(2);

                break;

        }
    }


    public void showChart(){
        //https://www.youtube.com/watch?v=jTYi0Q7lLco&list=PLFh8wpMiEi89LcBupeftmAcgDKCeC24bJ&index=12

        ArrayList<BarEntry> dataVals= new ArrayList<>();

        float interestGaind= 0.0F;
        int cat=getCategory();

        String label="";
        if( cat==0) {
            label="Total intest gained: ";

            List<Invest_Debt> myDebts= myDb.debt_readBonds(getForeighnkey());

            for(int i=0; i<myDebts.size();i++){
                float gain=(float) Math.round(myDebts.get(i).valueAtMaturity()-myDebts.get(i).getAmountBorred());
                dataVals.add(new BarEntry(i,gain));
                interestGaind+=gain;
            }
        }
        else if(cat==1){
            label="Total intest payable: ";
            List<Invest_Debt> myDebts= myDb.debt_readDebt(getForeighnkey());

            for(int i=0; i<myDebts.size();i++){
                float gain=(float) Math.round(myDebts.get(i).valueAtMaturity()-myDebts.get(i).getAmountBorred());
                dataVals.add(new BarEntry(i,gain));
                interestGaind+=gain;
            }
        }
        else{
            //stock
            dataVals.add(new BarEntry(0,20));
            dataVals.add(new BarEntry(1,30));
            dataVals.add(new BarEntry(2,40));
            dataVals.add(new BarEntry(3,50));
        }



        BarDataSet barDataSet= new BarDataSet(dataVals,String.format("%s: %.2f",label,interestGaind));
        BarData barData=new BarData();
        barData.addDataSet(barDataSet);

        stackedChart.setData(barData);



    }
    public void showHeading(){

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