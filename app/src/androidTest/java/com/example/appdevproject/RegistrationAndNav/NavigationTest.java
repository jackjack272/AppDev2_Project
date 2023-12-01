package com.example.appdevproject.RegistrationAndNav;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.appdevproject.Pages.Landing_Page;
import com.example.appdevproject.R;

import com.example.appdevproject.User.Registration_Page;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertNotNull;

//https://www.youtube.com/watch?v=sDp8JNbITm4&list=PLx5ipGeoOO2hCbqjX784_I4Y4mDsVbVEf&index=2
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NavigationTest{
    /**
     * Be able to click all the application's pages
     *
     * i know i can program to make the application back out then test another button but that's
     *      *  bleeding functionality and i dont like it for debugging sake
     *
     *
     * test question:
     *      can i get to all the pages?
     *      can i log out?
     */



    @Rule
    public ActivityTestRule <Registration_Page> myact= new ActivityTestRule <>(Registration_Page.class);

    //check the edit user page activates
    @Test
    public void clickEditUser(){
        new RegistrationTests().b_testLogIn();

        //click edit user.
        Espresso.onView(ViewMatchers.withId(R.id.btnEditProfile))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.change_email))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));


    }

    @Test
    public void clickBudget(){
        new RegistrationTests().b_testLogIn();


        //click edit user.
        Espresso.onView(ViewMatchers.withId(R.id.imgBudget))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.bud_monthlyExp))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.pressBack();
    }

    @Test
    public void clickDebt (){
        new RegistrationTests().b_testLogIn();


        //click edit user.
        Espresso.onView(ViewMatchers.withId(R.id.DebtCard))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.debt_repay_size))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void clickInvest (){
        new RegistrationTests().b_testLogIn();

        //click investment card
        Espresso.onView(ViewMatchers.withId(R.id.InvestmentCard))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.invest_fab_addOne))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void clickTax (){
        new RegistrationTests().b_testLogIn();

        //click edit user.
        Espresso.onView(ViewMatchers.withId(R.id.GoalCard))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.inc_labour))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void clickLogOut(){
        new RegistrationTests().b_testLogIn();

        //click edit user.
        Espresso.onView(ViewMatchers.withId(R.id.btnLogout))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.getUserName))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }

}