package com.example.appdevproject.Investment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.example.appdevproject.R;
import java.util.ArrayList;
import java.util.HashMap;

public class InvestmentActivity extends AppCompatActivity {

    Button btnAddNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);

        DbHandlerInvestment db = new DbHandlerInvestment(this);
        ArrayList<HashMap<String, String>> investmentList = db.GetInvestments();
        ListView lv = (ListView) findViewById(R.id.investList);
        ListAdapter adapter = new SimpleAdapter(InvestmentActivity.this, investmentList, R.layout.list_row,new String[]{"investname","investtype","investamount"}, new int[]{R.id.name, R.id.type, R.id.amount});
        lv.setAdapter(adapter);

        btnAddNew = findViewById(R.id.btnAddInves);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InvestmentActivity.this, NewInvestmentActivity.class));
            }
        });
    }
}