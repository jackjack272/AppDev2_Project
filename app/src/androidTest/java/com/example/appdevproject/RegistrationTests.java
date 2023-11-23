package com.example.appdevproject;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.appdevproject.DataBase.Interfaces.Users;
import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Pages.Landing_Page;
import com.example.appdevproject.User.Models.User;
import com.example.appdevproject.User.Registration_Page;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

//import org.mockito.Mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;

import static org.junit.Assert.assertNotNull;


import android.content.ComponentName;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.view.View;

//https://www.youtube.com/watch?v=sDp8JNbITm4&list=PLx5ipGeoOO2hCbqjX784_I4Y4mDsVbVEf&index=2
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RegistrationTests {
    @Rule
    public ActivityTestRule <Registration_Page> myact= new ActivityTestRule <>(Registration_Page.class);




    @Test
    public void testRegistrationVsSignInFormDisplay() {
        Espresso.onView(ViewMatchers.withId(R.id.switch1))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.isNotChecked()))
                .perform(ViewActions.click())
                .check(ViewAssertions.matches(ViewMatchers.isChecked()));

        Espresso.onView(ViewMatchers.withId(R.id.getDateOfBirth))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        Espresso.onView(ViewMatchers.withId(R.id.getEmail))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

    }
    @Test
    public void testButtonClickOpensLandingPage() {

        //toggle the button to log in
        Espresso.onView(ViewMatchers.withId(R.id.switch1))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.isNotChecked()))
                .perform(ViewActions.click());

        //click log in
        Espresso.onView(ViewMatchers.withId(R.id.nextPage)).perform(ViewActions.click());

        //see if the next page has the required username
        Espresso.onView(ViewMatchers.withId(R.id.textUserName))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }



}
