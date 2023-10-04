package com.example.appdevproject.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdevproject.R;


/**
 * This is page allows the user to make changes to their email, DOB and password.
 */

// This page has not been tested yet.

public class EditUserPage extends AppCompatActivity {
    private TextView banner;
    private EditText ch_name, ch_email, ch_dob, ch_pass, ch_confirm_pass;

    private SharedPreferences username;
    private Button save;

    UserDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_page);
        makeAssocications();

        greetUser("You want to change? okay but only this one time, ");

        //show the user the values they have already in the db
        autoFillTheFieldsWithvalues(); //not completed


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=getEditValues();
                if(!user.isEmpty()) {
                    db.updateUser(user);
                }
            }
        });

    }

    private void autoFillTheFieldsWithvalues(){
        try{
            User user=db.getUserByUsername(username.getString("username",""));
            ch_email.setHint(user.getEmail());
            ch_dob.setHint(user.getDob());


        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Failed to find user in Db", Toast.LENGTH_SHORT).show();
        }
    }

    private void makeAssocications() {
        banner = findViewById(R.id.editBanner);
//        ch_name = findViewById(R.id.change_name); //deleted this filed
        ch_email = findViewById(R.id.change_email);
        ch_dob = findViewById(R.id.change_dob);

        ch_pass = findViewById(R.id.changePword);
        ch_confirm_pass = findViewById(R.id.confirmPword);

        save = findViewById(R.id.edit_save_btn);
        //db
        db = new UserDb(EditUserPage.this);
    }

    private void greetUser(String greet) {
        username = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        banner.setText(greet + " " + username.getString("username", "no username found"));
    }

    private User getEditValues() {
        // make sure the passwords match
        if (ch_pass.getText().equals(ch_confirm_pass.getText())) {
            //get the rest of the object
            User updateMe= new User();
            updateMe.setEmpty(false);
            updateMe.setDob(String.valueOf(ch_dob.getText()));
            updateMe.setEmail(String.valueOf(ch_email.getText()));
            updateMe.setPassword(String.valueOf(ch_pass.getText()));

            return updateMe;
        } else {
            Toast.makeText(this, "The passwords don't match :(", Toast.LENGTH_SHORT).show();
            User xx=new User();
                xx.setEmpty(true);
            return   xx;
        }

    }


}