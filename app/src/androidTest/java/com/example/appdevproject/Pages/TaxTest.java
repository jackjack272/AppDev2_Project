package com.example.appdevproject.Pages;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.R;

import com.example.appdevproject.RegistrationAndNav.NavigationTest;
import com.example.appdevproject.User.Registration_Page;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import android.content.Context;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaxTest {

    @Rule
    public ActivityTestRule <Registration_Page> myact=
            new ActivityTestRule <>(Registration_Page.class);


    @Test
    public void a_testAddingIncome(){
        InvestTests.addDebt();
        InvestTests.addBond();


        new NavigationTest().clickTax();

        //click this FaB: inc_fab
        Espresso.onView(ViewMatchers.withId(R.id.inc_fab))
                .perform(ViewActions.click());

            //fill out the form.

                //jobtitle
            Espresso.onView(ViewMatchers.withId(R.id.tax_addnewCard_jobTitle))
                .perform(ViewActions.typeText("test"), ViewActions.closeSoftKeyboard());

                //hourly
            Espresso.onView(ViewMatchers.withId(R.id.tax_addnewCard_hourlyWage))
                    .perform(ViewActions.typeText("100"),ViewActions.closeSoftKeyboard());

                //hours
            Espresso.onView(ViewMatchers.withId(R.id.tax_addnewCard_hoursWorked))
                    .perform(ViewActions.typeText("40"),ViewActions.closeSoftKeyboard());

                //yearly Bonus
            Espresso.onView(ViewMatchers.withId(R.id.tax_addnewCard_bonuses))
                    .perform(ViewActions.typeText("5000"), ViewActions.closeSoftKeyboard());

            //click save.
            Espresso.onView(ViewMatchers.withId(R.id.tax_addnewCard_submit))
                    .perform(ViewActions.click());

        sleepMe(1);
        //is the obj in the db?
        Context context = ApplicationProvider.getApplicationContext();
        ProjectDb projectDb = new ProjectDb(context);

        int userId=projectDb.getUserById( projectDb.getUserByUsername("james smith").getUserName());
        if(projectDb.income_readAll(userId).size() ==0){
           fail("income was not added to db. ");
        }

    }

    @Test
    public void b_testRecyclerAdapters(){
        new NavigationTest().clickTax();

        //can i interact with the top one?
            Espresso.onView(ViewMatchers.withId(R.id.inc_labourRecycle))
                .perform(RecyclerViewActions.scrollToPosition(1));

        //can i interact with the bottom one?
        Espresso.onView(ViewMatchers.withId(R.id.inc_labourRecycle))
                .perform(RecyclerViewActions.scrollToPosition(2));

    }



//util
    private void sleepMe(int seconds){
        try{
            Thread.sleep(seconds*1000);
        }catch (Exception e){}
    }



}
