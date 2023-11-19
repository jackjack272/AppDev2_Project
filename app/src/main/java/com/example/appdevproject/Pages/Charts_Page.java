package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.appdevproject.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Charts_Page extends AppCompatActivity {

    ArrayList barArraylist;
    ArrayList pieArraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        BarChart barChart = findViewById(R.id.barchart);
        PieChart pieChart = findViewById(R.id.piechart);
        getData();
        BarDataSet barDataSet = new BarDataSet(barArraylist,"");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        //color bar data set
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        barChart.animateY(5000);

        //text color
        barDataSet.setValueTextColor(Color.BLACK);

        //settting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(true);

        PieDataSet pieDataSet = new PieDataSet(pieArraylist,"");
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        //color pie data set
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.animateXY(5000,5000);
    }

    private void getData()
    {
        barArraylist = new ArrayList();
        barArraylist.add(new BarEntry(2f,10));
        barArraylist.add(new BarEntry(3f,20));
        barArraylist.add(new BarEntry(4f,30));
        barArraylist.add(new BarEntry(5f,40));
        barArraylist.add(new BarEntry(6f,50));

        pieArraylist = new ArrayList<>();
        pieArraylist.add(new PieEntry(2f,10));
        pieArraylist.add(new PieEntry(3f,20));
        pieArraylist.add(new PieEntry(4f,30));
        pieArraylist.add(new PieEntry(5f,40));
        pieArraylist.add(new PieEntry(6f,50));
    }
}