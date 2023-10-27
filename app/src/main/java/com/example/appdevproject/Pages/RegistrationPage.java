package com.example.appdevproject.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

//<<<<<<< HEAD:app/src/main/java/com/example/appdevproject/Pages/RegistrationPage.java
import com.example.appdevproject.Utility.CustomException.MissingField;
import com.example.appdevproject.R;
import com.example.appdevproject.Utility.ProjectDb;
//=======
//import com.example.appdevproject.CustomException.MissingField;
//import com.example.appdevproject.LandingPage.IntroActivity;
//import com.example.appdevproject.LandingPage.LandingPage;
//import com.example.appdevproject.User.UserDb;
//>>>>>>> 33761130263a0631cbbdc82cebe34b13e61f2660:app/src/main/java/com/example/appdevproject/RegistrationPage.java
import com.example.appdevproject.User.User;

public class RegistrationPage extends AppCompatActivity {
    // i want a menue on the left hand side with all the avialble activities.

    /**
     *      The user is being saved to the database , but i need to check if the username exists
     *          username is going to be PK for the db     *
     */


    //the log in and registration dosent work properly ill have to ask prof for help
        // the id throws an error immediately, why?

    // how do i secure the db from sql injection attacks?

    ProjectDb projectDb;
    EditText userName, password, email, dateOfBirth;
    Switch toggle;
    Button nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration);

        makeAssociates();
        admin_prePopulate();

        projectDb =new ProjectDb(RegistrationPage.this);

        // i can get the username from the login screen
//         i can keep it in the shared prefrences and find user in db from the username.


        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    nextPage.setText("Login");
                    email.setVisibility(View.INVISIBLE);
                    dateOfBirth.setVisibility(View.INVISIBLE);
                    //log in
                }else{
                    nextPage.setText("Register");
                    email.setVisibility(View.VISIBLE);
                    dateOfBirth.setVisibility(View.VISIBLE);
                }
            }
        });

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean loggedIn=false;
                User user = null;

                if(toggle.isChecked()){
                    //login
                    if(!userName.getText().equals("")   ){
                        try{
                            user= projectDb.getUserByUsername(String.valueOf(userName.getText()));
                            // throws if there is no match
                        }catch (Exception e){
                            //throw toast saying user dosent extist
                            Toast.makeText(RegistrationPage.this, "User Dosent Exist :(- did you register?", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        return;
                    }

                    // have the user.
                    loggedIn=User.comparePasswords(user.getPassword(), String.valueOf(password.getText()));
                    if(!loggedIn){
                        Toast.makeText(RegistrationPage.this, "Password is wrong. ", Toast.LENGTH_SHORT).show();
                        return ;
                    }
                }



                else{
                    email.setVisibility(View.VISIBLE);
                    dateOfBirth.setVisibility(View.VISIBLE);
                    nextPage.setText("Register!");
                    //register
                    try{
                        // if the username dosent exist it will throw an error, else it will return with toast.
                        user= getUserRegistrationObject();
                        User x= projectDb.getUserByUsername(user.getUserName());
                        if(x !=null){
                            Toast.makeText(RegistrationPage.this, "This username exits, pick another one", Toast.LENGTH_SHORT).show();
                            return;// user exists so kill control
                        }
                    }catch (Exception e){
                        e.printStackTrace();

                        projectDb.makeUser(user); // make the user
                        Toast.makeText(RegistrationPage.this, "user was registered", Toast.LENGTH_SHORT).show();
                    }
                }

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putString("username",user.getUserName());
                myEdit.apply();

                // go next intent.


                Toast.makeText(RegistrationPage.this, "Success! Welcome in!", Toast.LENGTH_SHORT).show();
                //sleep 750 milsec

                startActivity(new Intent(RegistrationPage.this, LandingPage.class));
            }
        });

    }


    private void admin_prePopulate(){
        userName.setText("James James");
        password.setText("apple1");
        email.setText("james@gmail.com");
        dateOfBirth.setText("2222");
    }

    private void makeAssociates(){
        userName=findViewById(R.id.getUserName);
        password=findViewById(R.id.getPassword);
        email=findViewById(R.id.getEmail);
        dateOfBirth=findViewById(R.id.getDateOfBirth);
        toggle=findViewById(R.id.switch1);

        nextPage=findViewById(R.id.nextPage);

    };

    private User getUserRegistrationObject() throws MissingField {
        String msg="please fill me";
        Boolean filledFields=true;
        if( String.valueOf(userName.getText()).equals("") ){
            userName.setHint(msg);
            filledFields=false;
            throw new MissingField();
        } else if (String.valueOf( password.getText()).equals("")) {
            password.setHint(msg);
            filledFields=false;
            throw new MissingField();
        }else if(String.valueOf( email.getText()).equals("")){
            email.setHint(msg);
            filledFields=false;
            throw new MissingField();
        } else if (String.valueOf( dateOfBirth.getText()).equals("")) {
            dateOfBirth.setHint(msg);
            filledFields=false;
            throw new MissingField();
        }



        if(filledFields) {
            return new User(
                    String.valueOf(userName.getText()),
                    String.valueOf(password.getText()),
                    String.valueOf(email.getText()),
                    String.valueOf(dateOfBirth.getText())
            );
        }
        throw new MissingField();
    }




}