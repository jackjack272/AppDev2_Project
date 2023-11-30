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
public class NavigationTest extends RegistrationTests{
    /**
     * Be able to click all the application's pages
     *
     * i know i can program to make the application back out then test another button but that's
     *      *  bleeding functionality and i dont like it for debugging sake
     *
     */

    @Rule
    public ActivityTestRule <Registration_Page> myact= new ActivityTestRule <>(Registration_Page.class);

    //check the edit user page activates
    @Test
    public void clickEditUser(){
        testButtonClickOpensLandingPage();

        //click edit user.
        Espresso.onView(ViewMatchers.withId(R.id.btnEditProfile))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.change_email))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));


    }

    @Test
    public void clickBudget(){
        testButtonClickOpensLandingPage();

        //click edit user.
        Espresso.onView(ViewMatchers.withId(R.id.imgBudget))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.bud_monthlyExp))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));



    }

    @Test
    public void clickDebt (){
        testButtonClickOpensLandingPage();

        //click edit user.
        Espresso.onView(ViewMatchers.withId(R.id.DebtCard))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.debt_repay_size))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));


    }

    @Test
    public void clickInvest (){

        testButtonClickOpensLandingPage();

        //click edit user.
        Espresso.onView(ViewMatchers.withId(R.id.InvestmentCard))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.invest_fab_addOne))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void clickTax (){
        testButtonClickOpensLandingPage();

        //click edit user.
        Espresso.onView(ViewMatchers.withId(R.id.GoalCard))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.inc_labour))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void clickLogOut(){
        testButtonClickOpensLandingPage();

        //click edit user.
        Espresso.onView(ViewMatchers.withId(R.id.btnLogout))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.getUserName))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }

}