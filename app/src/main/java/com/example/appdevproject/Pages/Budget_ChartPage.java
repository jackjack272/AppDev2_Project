package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.appdevproject.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Budget_ChartPage extends AppCompatActivity {
    ArrayList pieArraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_chart);

        Intent intent = getIntent();
        PieChart pieChart = findViewById(R.id.budgetpiechart);
        getData(intent);

        PieDataSet pieDataSet = new PieDataSet(pieArraylist,"");
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        //color pie data set
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.animateXY(5000,5000);
    }

    private void getData(Intent intent)
    {
        float totHousing = intent.getFloatExtra("totHousing", 0.0f);
        float totUtility = intent.getFloatExtra("totUtility",0.0f);
        float totTransport = intent.getFloatExtra("totTransport",0.0f);
        float totFood = intent.getFloatExtra("totFood",0.0f);
        float totEntertain = intent.getFloatExtra("totEntertain",0.0f);
        pieArraylist = new ArrayList<>();
        pieArraylist.add(new PieEntry(totHousing,totHousing));
        pieArraylist.add(new PieEntry(totUtility,totUtility));
        pieArraylist.add(new PieEntry(totTransport,totTransport));
        pieArraylist.add(new PieEntry(totFood,totFood));
        pieArraylist.add(new PieEntry(totEntertain,totEntertain));
    }
}