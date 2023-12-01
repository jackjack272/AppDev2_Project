package com.example.appdevproject.z_oldImplements.z_Loans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appdevproject.User.Registration_Page;
import com.example.appdevproject.R;


public class OnboardingActivity extends AppCompatActivity {
    Button getStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        getStarted = findViewById(R.id.btngetStarted);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnboardingActivity.this,
                        Registration_Page.class));
            }
        });
    }
}