package com.example.appdevproject.Navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.example.appdevproject.Budget.Model.Item;
import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.components.Legend;

import java.util.ArrayList;
import java.util.List;

public class Charts_Page extends AppCompatActivity {

    ArrayList barArraylist;
    ArrayList pieArraylist;
    private ProjectDb myDb;
    float totHousing = 0;
    float totUtility = 0;
    float totTransport = 0;
    float totFood = 0;
    float totEntertain = 0;
    float totDebt = 0;
    float totBonds = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        myDb = new ProjectDb(Charts_Page.this);

        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= myDb.getUserById(s.getString("username",""));
        List<Item> myItems= myDb.item_getAll(foreignKey);

        for (int i = 0; i < myItems.size(); i++){
            if(myItems.get(i).getCategory() == 0){
                totHousing = totHousing + myItems.get(i).getPriceOfItem().floatValue();
            } else if (myItems.get(i).getCategory() == 1) {
                totUtility = totUtility + myItems.get(i).getPriceOfItem().floatValue();
            } else if (myItems.get(i).getCategory() == 2) {
                totTransport = totTransport + myItems.get(i).getPriceOfItem().floatValue();
            } else if (myItems.get(i).getCategory() == 3) {
                totFood = totFood + myItems.get(i).getPriceOfItem().floatValue();
            } else {
                totEntertain = totEntertain + myItems.get(i).getPriceOfItem().floatValue();
            }
        }

        List<Invest_Debt> myLoans= myDb.debt_readDebt(foreignKey);
        List<Invest_Debt> myBonds= myDb.debt_readBonds(foreignKey);

        for (int i = 0; i < myLoans.size(); i++){
            totDebt = totDebt + myLoans.get(i).getAmountBorred().floatValue();
        }

        for (int i = 0; i < myBonds.size(); i++){
            totBonds = totBonds + myBonds.get(i).getAmountBorred().floatValue();
        }

        BarChart barChart = findViewById(R.id.barchart);
        PieChart pieChart = findViewById(R.id.piechart);
        getData();

        PieDataSet pieDataSet = new PieDataSet(pieArraylist,"");
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        //color pie data set
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(15f);
        pieDataSet.setSliceSpace(3f);
        pieData.setDrawValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setRotationEnabled(true);

        pieChart.animateXY(5000,5000);

        Legend legend = pieChart.getLegend();
        legend.setTextSize(14f);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL); // Set orientation to vertical
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER); // Adjust vertical alignment
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // Adjust horizontal alignment
        legend.setDrawInside(false); // Set to false to position the legend outside the chart

        BarDataSet barDataSet = new BarDataSet(barArraylist,"");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        //color bar data set
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        barChart.animateY(5000);

        //text color
        barDataSet.setValueTextColor(Color.BLACK);
        barData.setDrawValues(true);

        //settting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

        // Hide the legend
        Legend legend2 = barChart.getLegend();
        legend2.setEnabled(false);

        // Customize X-axis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        // Set string values for x-axis
        final String[] labels = new String[]{"","Bonds", "Debts"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

    }

    private void getData()
    {
        barArraylist = new ArrayList<>();
        barArraylist.add(new BarEntry(1f, totBonds));
        barArraylist.add(new BarEntry(2f, totDebt));

        pieArraylist = new ArrayList<>();
        if(totHousing != 0){
            pieArraylist.add(new PieEntry(totHousing,"Housing($)"));
        }
        if(totUtility != 0){
            pieArraylist.add(new PieEntry(totUtility,"Utility($)"));
        }
        if(totTransport != 0){
            pieArraylist.add(new PieEntry(totTransport,"Transportation($)"));
        }
        if(totFood != 0){
            pieArraylist.add(new PieEntry(totFood,"Food($)"));
        }
        if(totEntertain != 0){
            pieArraylist.add(new PieEntry(totEntertain,"Entertainment($)"));
        }
    }
}