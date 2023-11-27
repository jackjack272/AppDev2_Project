package com.example.appdevproject.Investment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Adapters.SumsAdapter;
import com.example.appdevproject.Investment.Fab.Invest_AddNewInvest;
import com.example.appdevproject.z_oldImplements.z_Loans.Invest_Db_old;
import com.example.appdevproject.z_oldImplements.z_Loans.Invest_AddNew_old;
import com.example.appdevproject.Investment.Models.Totals_Find;
import com.example.appdevproject.Investment.Models.Totals_Save;
import com.example.appdevproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Invest_Page extends AppCompatActivity {

//recycler view implementation
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SumsAdapter sumsAdapter;
    ProjectDb myDb;


//Page display stuff
    Button btnAddNew;
    TextView marketVale;

    FloatingActionButton fab;

    Double totalInvested=0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);

        makeAssociations();

        setAdapter();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Invest_AddNewInvest.newInstace()
                    .show(
                        getSupportFragmentManager(),
                        Invest_AddNewInvest.TAG
                    );
            }
        });

        makeHeading();


    }

    public void makeHeading(){
        marketVale.setText(String.format("$%.2f", this.totalInvested));
    }

    public void makeAssociations(){
        fab=findViewById(R.id.invest_fab_addOne);
        recyclerView=findViewById(R.id.invest_list);
        marketVale=findViewById(R.id.invest_maketValue);

        myDb= new ProjectDb(Invest_Page.this);
    }


    public void setAdapter(){
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        sumsAdapter = new SumsAdapter(Invest_Page.this);
        sumsAdapter.setSums(getSums());
        recyclerView.setAdapter(sumsAdapter);
    }

    public int getForenKey(){

        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= myDb.getUserById(s.getString("username",""));
        return foreignKey;
    }


    public List<Totals_Save> getSums(){

        List<Totals_Save> myTotals =new ArrayList<>();
        Totals_Find xx= new Totals_Find(Invest_Page.this,getForenKey());

        Totals_Save bonds= xx.getBonds();
        if(bonds != null){
            myTotals.add(bonds);
            totalInvested+=bonds.getTotalAmount();

        }


        bonds=xx.getDebt();
        if(bonds!=null){
            myTotals.add(bonds);
            totalInvested-=bonds.getTotalAmount();
        }

//        bonds=(xx.getStock());
//        if(bonds !=null){
//            myTotals.add(xx.getStock());
//        }

        return myTotals;
    }






    public void ogImpl(){
        Invest_Db_old db = new Invest_Db_old(this);
        ArrayList<HashMap<String, String>> investmentList = db.GetInvestments();
        ListView lv = (ListView) findViewById(R.id.invest_list);

        ListAdapter adapter = new SimpleAdapter(
                Invest_Page.this,
                investmentList,
                R.layout.list_row,
                new String[]{"investname","investtype","investamount"},
                new int[]{R.id.name, R.id.type, R.id.amount}
        );

        lv.setAdapter(adapter);
//        btnAddNew = findViewById(R.id.btnAddInves);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Invest_Page.this, Invest_AddNew_old.class));

            }
        });

    }


}