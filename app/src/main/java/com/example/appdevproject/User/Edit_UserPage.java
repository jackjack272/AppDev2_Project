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
import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.User.Models.User;


/**
 * This is page allows the user to make changes to their email, DOB and password.
 */

// This page has not been tested yet.

public class Edit_UserPage extends AppCompatActivity {
    private TextView banner;
    private EditText ch_name, ch_email, ch_dob, ch_pass, ch_confirm_pass;
    private SharedPreferences username;
    private Button save;
    ProjectDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit);
        makeAssocications();

        greetUser("EDIT PROFILE");

        //show the user the values they have already in the db
        autoFillTheFieldsWithvalues(); //not completed

//        adminSetValues();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=getEditValues();

                if(!user.isEmpty()) {
                    user.setPassword( User.md5HashEncrypt(user.getPassword()));
                    db.updateUser(user);
                }
                Toast.makeText(Edit_UserPage.this, "User Updated", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void adminSetValues(){
        ch_email.setText("admin@test.com");
        ch_dob.setText("01/02/2023");
        ch_pass.setText("apple2");
        ch_confirm_pass.setText("apple2");
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
        db = new ProjectDb(Edit_UserPage.this);
    }

    private void greetUser(String greet) {
        username = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        //banner.setText(greet + " " + username.getString("username", "no username found"));
        banner.setText(greet);
    }

    private User getEditValues() {
        // make sure the passwords match
        if (ch_pass.getText().toString().equals(ch_confirm_pass.getText().toString())) {
            //get the rest of the object
            User updateMe= new User();
            updateMe.setEmpty(false);
            updateMe.setDob(String.valueOf(ch_dob.getText().toString()));
            updateMe.setEmail(String.valueOf(ch_email.getText().toString()));
            updateMe.setPassword(String.valueOf(ch_pass.getText().toString()));
            updateMe.setId(getForeighnkey());


            return updateMe;
        } else {
            Toast.makeText(this, "The passwords don't match :(", Toast.LENGTH_SHORT).show();
            User xx=new User();
                xx.setEmpty(true);
            return   xx;
        }

    }


    public int getForeighnkey() {
        SharedPreferences s=getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int foreignKey= db.getUserById(s.getString("username",""));
        return foreignKey;
    }

}