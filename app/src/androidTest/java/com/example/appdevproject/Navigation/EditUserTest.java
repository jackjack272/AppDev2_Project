package com.example.appdevproject.Navigation;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.R;
import com.example.appdevproject.RegistrationAndNav.NavigationTest;
import com.example.appdevproject.User.Models.User;

import com.example.appdevproject.User.Registration_Page;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.fail;


import android.content.Context;

//https://www.youtube.com/watch?v=sDp8JNbITm4&list=PLx5ipGeoOO2hCbqjX784_I4Y4mDsVbVEf&index=2
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EditUserTest {
    /**
     * do the changes
     */

    @Rule
    public ActivityTestRule <Registration_Page> myact= new ActivityTestRule <>(Registration_Page.class);



    @Test
    public void updateUser(){
        new NavigationTest().clickEditUser();

        //update fields
        Espresso.onView(ViewMatchers.withId(R.id.changePword))
                .perform(ViewActions.typeText("apple2"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.confirmPword))
                .perform(ViewActions.typeText("apple2"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.change_email))
                .perform(ViewActions.typeText("joneChanged@hotmail.com"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.change_dob))
                .perform(ViewActions.typeText("05/13/2011"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.edit_save_btn))
                .perform(ViewActions.click());

        if(check_updatedValues()){
            fail("edit user values did not update");
        }
    }

    private boolean check_updatedValues(){
        //true values did not update.

        Context context = ApplicationProvider.getApplicationContext();
        ProjectDb projectDb = new ProjectDb(context);


        User user= projectDb.getUserByUsername("james smith");

        if(user==null){
            return true;
        }
        if(!user.getDob().equals("05/13/2011")){
            return true;
        }
        if(!user.getEmail().equals("joneChanged@hotmail.com")){
            return true;
        }

        return false;
    }




}
