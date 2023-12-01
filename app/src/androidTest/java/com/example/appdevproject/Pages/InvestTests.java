package com.example.appdevproject.Pages;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.Investment.Models.Invest_Debt;

import com.example.appdevproject.RegistrationAndNav.NavigationTest;

import com.example.appdevproject.User.Registration_Page;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.example.appdevproject.R;


import android.content.Context;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InvestTests {
    /**
     * This page is mean tto test the invest page.
     *
     */

    @Rule
    public ActivityTestRule <Registration_Page> myact= new ActivityTestRule <>(Registration_Page.class);


//debt section.
    @Test
    public void a_testAddDebtToDb(){
        new NavigationTest().clickInvest();

        //click fab
        Espresso.onView(ViewMatchers.withId(R.id.invest_fab_addOne))
                .perform(ViewActions.click());

        //click the button for Debt
        Espresso.onView(ViewMatchers.withId(R.id.invest_add_debt))
                .perform(ViewActions.click());

        // Check if the TextView with the specified ID has the expected text
        Espresso.onView(ViewMatchers.withId(R.id.invest_debt_heading))
                .check(ViewAssertions.matches(ViewMatchers.withText("New Debt")));

        //fill out the form.
            Espresso.onView(ViewMatchers.withId(R.id.invest_debt_Name))
                    .perform(ViewActions.typeText("testing"),
                            ViewActions.closeSoftKeyboard());

            Espresso.onView(ViewMatchers.withId(R.id.invest_debt_getAmountBorrowed))
                    .perform(ViewActions.typeText("1000.00"),
                            ViewActions.closeSoftKeyboard());

            Espresso.onView(ViewMatchers.withId(R.id.invest_debt_getInterestRate))
                    .perform(ViewActions.typeText("11.5"),
                            ViewActions.closeSoftKeyboard());

            Espresso.onView(ViewMatchers.withId(R.id.invest_debt_getCompoundsPerYear))
                    .perform(ViewActions.typeText("6"),
                            ViewActions.closeSoftKeyboard());

            Espresso.onView(ViewMatchers.withId(R.id.invest_debt_getTimeInMonths))
                    .perform(ViewActions.typeText("24"),
                            ViewActions.closeSoftKeyboard());

        //save
        Espresso.onView(ViewMatchers.withId(R.id.invest_debt_btn))
                .perform(ViewActions.click());

        Context context = ApplicationProvider.getApplicationContext();
        ProjectDb projectDb = new ProjectDb(context);
        if (null == projectDb.getUserByUsername("james smith")) {
            fail("user was not found in db.");
        }

        String username= projectDb.getUserByUsername("james smith").getUserName();
        if(0 == projectDb.debt_readDebt(projectDb.getUserById(username)).size()){
            fail("item was not added to db");
        }



    }
    @Test
    public void b_testEditDebt(){ //if there are multiple edit buttons it will crash
        new NavigationTest().clickInvest();
//
//        //what is the amount in the heading? //unmute me once other parts are done
        Espresso.onView(ViewMatchers.withId(R.id.invest_maketValue))
                .check(ViewAssertions.matches(ViewMatchers.withText("$-1000.00")));

        //click on recycler view
        Espresso.onView(ViewMatchers.withId(R.id.invest_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        //position 1 is debts, its always there


        //is there an item there?
//        Espresso.onView(ViewMatchers.withId(R.id.invest_choice_card_recycleview))
//                .perform(RecyclerViewActions.scrollToPosition(0));


        //click edit
        Espresso.onView(ViewMatchers.withId(R.id.invest_choice_card_recycleview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

//        try{
//            Thread.sleep(2000);
//        }catch (Exception e){}

        Espresso.onView(ViewMatchers.withId(R.id.invest_bond_card_edit))
                .perform(ViewActions.click());

        //am i at the edit card page?
        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_heading))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //fill out the edit form
        //fill out the form.
        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_name))
                .perform(ViewActions.typeText("testing-edited"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_amountBorrowed))
                .perform(ViewActions.typeText("200.00"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_interestRate))
                .perform(ViewActions.typeText("1.5"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_compounds))
                .perform(ViewActions.typeText("5"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_months))
                .perform(ViewActions.typeText("14"),
                        ViewActions.closeSoftKeyboard());

        //save button
        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_edit))
                .perform(ViewActions.click());


        //is the card still there?
        Espresso.onView(ViewMatchers.withId(R.id.invest_choice_card_recycleview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        try{
            Thread.sleep(200);
        }catch (Exception e){};

        //did the changes appear?
        Espresso.onView(ViewMatchers.withId(R.id.invest_bond_card_heading))
                .check(ViewAssertions.matches(ViewMatchers.withText("testing-edited")));

    }
    @Test
    public void c_testDeleteDebt(){
        new NavigationTest().clickInvest();

        //click on recycler view
        Espresso.onView(ViewMatchers.withId(R.id.invest_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        //position 1 is debts, its always there

        //is there an item there?
        Espresso.onView(ViewMatchers.withId(R.id.invest_choice_card_recycleview))
                .perform(RecyclerViewActions.scrollToPosition(0));

        //click delete
        Espresso.onView(ViewMatchers.withId(R.id.invest_choice_card_recycleview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        Espresso.onView(ViewMatchers.withId(R.id.invest_bond_card_delete))
                .perform(ViewActions.click());

        //did the edit button disapear
        Espresso.onView(ViewMatchers.withId(R.id.invest_bond_card_edit))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

        Context context = ApplicationProvider.getApplicationContext();
        ProjectDb projectDb = new ProjectDb(context);
        if (null == projectDb.getUserByUsername("james smith")) {
            fail("user was not found in db. ");
        }

        int userId=projectDb.getUserById( projectDb.getUserByUsername("james smith").getUserName());
        Invest_Debt debt= projectDb.debt_readOne(userId);
        if(debt !=null){
            fail("Debt wasent deleted properly");
        }

    }
//bonds section

    @Test
    public void d_testAddBondToDb(){ //not yet implemented just copied and pasted
        new NavigationTest().clickInvest();

        //click fab
        Espresso.onView(ViewMatchers.withId(R.id.invest_fab_addOne))
                .perform(ViewActions.click());

        //click the button for bond
        Espresso.onView(ViewMatchers.withId(R.id.invest_add_bond))
                .perform(ViewActions.click());


        // Check if the TextView with the specified ID has the expected text
        Espresso.onView(ViewMatchers.withId(R.id.invest_debt_heading))
                .check(ViewAssertions.matches(ViewMatchers.withText("New Bond")));

        //fill out the form.
        Espresso.onView(ViewMatchers.withId(R.id.invest_debt_Name))
                .perform(ViewActions.typeText("testing"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.invest_debt_getAmountBorrowed))
                .perform(ViewActions.typeText("1000.00"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.invest_debt_getInterestRate))
                .perform(ViewActions.typeText("11.5"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.invest_debt_getCompoundsPerYear))
                .perform(ViewActions.typeText("6"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.invest_debt_getTimeInMonths))
                .perform(ViewActions.typeText("24"),
                        ViewActions.closeSoftKeyboard());

        //save
        Espresso.onView(ViewMatchers.withId(R.id.invest_debt_btn))
                .perform(ViewActions.click());

        Context context = ApplicationProvider.getApplicationContext();
        ProjectDb projectDb = new ProjectDb(context);
        if (null == projectDb.getUserByUsername("james smith")) {
            fail("user was not found in db.");
        }

        String username= projectDb.getUserByUsername("james smith").getUserName();
        if(1 != projectDb.debt_readBonds(projectDb.getUserById(username)).size()){
            fail("item was not added to db");
        }

    }
    @Test
    public void e_testEditBond(){ //if there are multiple edit buttons it will crash
        new NavigationTest().clickInvest();
//
//        //what is the amount in the heading? //unmute me once other parts are done
//        Espresso.onView(ViewMatchers.withId(R.id.invest_maketValue))
//                .check(ViewAssertions.matches(ViewMatchers.withText("$-1000.00")));

        //click on recycler view
        Espresso.onView(ViewMatchers.withId(R.id.invest_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        //position 1 is debts, its always there


        //is there an item there?
        Espresso.onView(ViewMatchers.withId(R.id.invest_choice_card_recycleview))
                .perform(RecyclerViewActions.scrollToPosition(0));


        //click edit
        Espresso.onView(ViewMatchers.withId(R.id.invest_choice_card_recycleview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        Espresso.onView(ViewMatchers.withId(R.id.invest_bond_card_edit))
                .perform(ViewActions.click());

        //am i at the edit card page?
        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_heading))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        //fill out the edit form
        //fill out the form.
        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_name))
                .perform(ViewActions.typeText("testing-edited"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_amountBorrowed))
                .perform(ViewActions.typeText("200.00"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_interestRate))
                .perform(ViewActions.typeText("1.5"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_compounds))
                .perform(ViewActions.typeText("5"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_months))
                .perform(ViewActions.typeText("14"),
                        ViewActions.closeSoftKeyboard());

        //save button
        Espresso.onView(ViewMatchers.withId(R.id.invest_edit_edit))
                .perform(ViewActions.click());


        //is the card still there?
        Espresso.onView(ViewMatchers.withId(R.id.invest_choice_card_recycleview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        try{
            Thread.sleep(200);
        }catch (Exception e){};

        //did the changes appear?
        Espresso.onView(ViewMatchers.withId(R.id.invest_bond_card_heading))
                .check(ViewAssertions.matches(ViewMatchers.withText("testing-edited")));

    }
    @Test
    public void f_testDeleteBond(){
        new NavigationTest().clickInvest();

        //click on recycler view
        Espresso.onView(ViewMatchers.withId(R.id.invest_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        //position 1 is debts, its always there

        //is there an item there?
        Espresso.onView(ViewMatchers.withId(R.id.invest_choice_card_recycleview))
                .perform(RecyclerViewActions.scrollToPosition(1));

        //click delete
        Espresso.onView(ViewMatchers.withId(R.id.invest_choice_card_recycleview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        Espresso.onView(ViewMatchers.withId(R.id.invest_bond_card_delete))
                .perform(ViewActions.click());

        //did the edit button disapear
        Espresso.onView(ViewMatchers.withId(R.id.invest_bond_card_edit))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

        Context context = ApplicationProvider.getApplicationContext();
        ProjectDb projectDb = new ProjectDb(context);
        if (null == projectDb.getUserByUsername("james smith")) {
            fail("user was not found in db. ");
        }

        int userId=projectDb.getUserById( projectDb.getUserByUsername("james smith").getUserName());
        Invest_Debt debt= projectDb.debt_readOne(userId);
        if(debt !=null){
            fail("Debt wasent deleted properly");
        }

    }


//External utils
    public static void addDebt(){
        new InvestTests().a_testAddDebtToDb();
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();

        //toggle register
        Espresso.onView(ViewMatchers.withId(R.id.switch1))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.getUserName))
                .perform(ViewActions.clearText());

        Espresso.onView(ViewMatchers.withId(R.id.getPassword))
                .perform(ViewActions.clearText());

    }

    public static void addBond(){
        new InvestTests().d_testAddBondToDb();
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();

        //toggle register
        Espresso.onView(ViewMatchers.withId(R.id.switch1))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.getUserName))
                .perform(ViewActions.clearText());

        Espresso.onView(ViewMatchers.withId(R.id.getPassword))
                .perform(ViewActions.clearText());

    }



}


//meant fir a_ but it breaks the code.
//click back
//        Espresso.pressBack();

//        //click investment card
//        Espresso.onView(ViewMatchers.withId(R.id.InvestmentCard))
//          .perform(ViewActions.click());
//this bad boy breaks the tests....


//        //what is the amount in the heading?
//        Espresso.onView(ViewMatchers.withId(R.id.invest_maketValue))
//                .check(ViewAssertions.matches(ViewMatchers.withText("$-1000.00")));

//            //check if total invested is right
//            //check if avg yearly % is right.
//            //check if monthly payout is right.
//
//
//        //click on recycler view
//        Espresso.onView(ViewMatchers.withId(R.id.invest_list))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
//        //position 1 is debts, its always there
//
//        //is the item there?
//
//        // Perform a scroll action to make sure the item is visible (optional)
//        Espresso.onView(ViewMatchers.withId(R.id.invest_choice_card_recycleview))
//                .perform(RecyclerViewActions.scrollTo(ViewMatchers.withId(0)));
//            //scroll to the 0'th position
//
//
//        // Check if the RecyclerView contains the expected value
//        Espresso.onView(ViewMatchers.withId(R.id.invest_bond_card_heading))
//                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

//delete the item in the db based on this user id
