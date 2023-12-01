package com.example.appdevproject.Pages;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.appdevproject.Pages.Landing_Page;
import com.example.appdevproject.R;

import com.example.appdevproject.RegistrationAndNav.NavigationTest;
import com.example.appdevproject.User.Registration_Page;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RepayPage {
    /**
     *
     * Test question:
     *  Can the user sort their debts by size? how about by interest?
     *
     */

    @Rule
    public ActivityTestRule <Registration_Page> myact=
            new ActivityTestRule <>(Registration_Page.class);



    @Test
    public void a_repaymentBySize(){
//        new InvestTests().a_testAddDebtToDb();
        //register page need to toggle switch so it can log in with clickDebt()
//        Espresso.onView(ViewMatchers.withId(R.id.switch1))
//                .perform(ViewActions.click());


        new NavigationTest().clickDebt();


        //click the size button.
        Espresso.onView(ViewMatchers.withId(R.id.debt_repay_size))
                .perform(ViewActions.click());

        //content in the recycler view?
            Espresso.onView(ViewMatchers.withId(R.id.debt_repay_debts))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
            //position 1 is debts, its always there
            Espresso.onView(ViewMatchers.withId(R.id.debt_repay_schedule))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
            //position 1 is debts, its always there

        //enter $50 into the field
        Espresso.onView(ViewMatchers.withId(R.id.debt_repay_extra))
                .perform(ViewActions.typeText("50"))
                .perform(ViewActions.closeSoftKeyboard());

        try{
            Thread.sleep(750);
        }catch (Exception e){}

        //click the button again.
        Espresso.onView(ViewMatchers.withId(R.id.debt_repay_size))
                .perform(ViewActions.click());

        //dose the top recycler have items?
            Espresso.onView(ViewMatchers.withId(R.id.debt_repay_debts))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
            //position 1 is debts, its always there
            Espresso.onView(ViewMatchers.withId(R.id.debt_repay_schedule))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
            //position 1 is debts, its always there




        //dose the bottom recycler value values?

    }

    @Test
    public void b_repaymentByPercent(){

//        new InvestTests().a_testAddDebtToDb();
        new NavigationTest().clickDebt();


        //click the size button.
        Espresso.onView(ViewMatchers.withId(R.id.debt_repay_interest))
                .perform(ViewActions.click());

        //content in the recycler view?
        Espresso.onView(ViewMatchers.withId(R.id.debt_repay_debts))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        //position 1 is debts, its always there
        Espresso.onView(ViewMatchers.withId(R.id.debt_repay_schedule))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        //position 1 is debts, its always there

        //enter $50 into the field
        Espresso.onView(ViewMatchers.withId(R.id.debt_repay_extra))
                .perform(ViewActions.typeText("50"))
                .perform(ViewActions.closeSoftKeyboard());

        try{
            Thread.sleep(750);
        }catch (Exception e){}

        //click the button again.
        Espresso.onView(ViewMatchers.withId(R.id.debt_repay_interest))
                .perform(ViewActions.click());

        //dose the top recycler have items?
        Espresso.onView(ViewMatchers.withId(R.id.debt_repay_debts))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        //position 1 is debts, its always there
        Espresso.onView(ViewMatchers.withId(R.id.debt_repay_schedule))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        //position 1 is debts, its always there




        //dose the bottom recycler value values?

    }




}
