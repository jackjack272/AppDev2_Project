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
//        float totHousing = Float.valueOf(intent.getStringExtra("totHousing"));
//        float totUtility = Float.parseFloat(intent.getStringExtra("totUtility"));
//        float totTransport = Float.parseFloat(intent.getStringExtra("totTransport"));
//        float totFood = Float.parseFloat(intent.getStringExtra("totFood"));
//        float totEntertain = Float.parseFloat(intent.getStringExtra("totEntertain"));
        pieArraylist = new ArrayList<>();
        pieArraylist.add(new PieEntry(200f,200));
        pieArraylist.add(new PieEntry(30f,30));
        pieArraylist.add(new PieEntry(80f,80));
        pieArraylist.add(new PieEntry(150f,150));
        pieArraylist.add(new PieEntry(50f,50));
    }
}