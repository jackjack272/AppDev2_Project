package com.example.appdevproject.Loans;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.appdevproject.R;

public class Loans_old extends AppCompatActivity {
    private Switch investIn;
    private Button size, interes, div;

    private EditText overMinRepay;
    private TextView monthlyLoss, monthlyGain;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loans);
        makeAssociations();


    }

    public void makeAssociations(){

    }
}