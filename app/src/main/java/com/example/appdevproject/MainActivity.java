package com.example.appdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    // i want a menue on the left hand side with all the avialble activities.

    EditText userName, password, email, dateOfBirth;
    Switch toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeAssociateions();

        // i can get the username from the login screen
        // i can keep it in the shared prefrences and find user in db from the username.

        //check if login or register
        if(toggle.isActivated()==false){ //register

        }else{ //login

        }

        //check for empty fields.




        //after creating user
            // 1 sec service to display toast : "success"
        //save the log in number as a shared preference-- only way to do it?
    }

    private void makeAssociateions(){
        userName=findViewById(R.id.getUserName);
        password=findViewById(R.id.getPassword);
        email=findViewById(R.id.getEmail);
        dateOfBirth=findViewById(R.id.getDateOfBirth);
        toggle=findViewById(R.id.regToggle);
    };




}