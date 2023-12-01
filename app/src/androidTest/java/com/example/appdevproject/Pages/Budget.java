package com.example.appdevproject.Pages;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.action.ViewActions;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.R;

import com.example.appdevproject.RegistrationAndNav.NavigationTest;
import com.example.appdevproject.User.Registration_Page;
import com.example.appdevproject.Utility.Utility;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.hasToString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import android.content.Context;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Budget {

    @Rule
    public ActivityTestRule <Registration_Page> myact= new ActivityTestRule <>(Registration_Page.class);


    @Test
    public void a_testAddItem(){
        new NavigationTest().clickBudget();

        //click the fab button
        onView(withId(R.id.bud_fab))
                .perform(click());

    //chat GPT generated test
        //stack over flow answers werent helpfun
        //own impl wasnsent working.
        //can you even
        // Click on the Spinner to open the dropdown
        Espresso.onView(ViewMatchers.withId(R.id.item_getCategory))
                .perform(ViewActions.click());

        // Select the item at position 0 in the spinner
        Espresso.onData(Matchers.anything())
                .inAdapterView(ViewMatchers.withId(R.id.item_getCategory))
                .atPosition(1)
                .perform(ViewActions.click());

        // Check if the selected value at position 0 is displayed in the Spinner
        Espresso.onView(ViewMatchers.withId(R.id.item_getCategory))
                .check(ViewAssertions.matches(withSpinnerText(Matchers.any(String.class))));


        //from string: item_category
//        fillTheForm("Housing");
//        fillTheForm("Utility");
//        fillTheForm("Transportation");
//        fillTheForm("Food");
//        fillTheForm("Entertainment");


        //test recycler scroll view

        //test category selection

        //test the second FAB

    }

    @Test
    public void b_testItemInDb(){

        Context context = ApplicationProvider.getApplicationContext();
        ProjectDb projectDb = new ProjectDb(context);
        int user_id= projectDb.getUserByUsername("james smith").getId();

        if(0==projectDb.item_getAll(user_id).size()){
            fail("budget items not in db ");
        }
    }

    @Test
    public void c_testDifferentCategorySelection(){
        new NavigationTest().clickBudget();

        clickTabs("Housing"); //added time delay in method to see actions
        clickTabs("Utility");
        clickTabs("Food");
        clickTabs("Transport");
        clickTabs("Entertain");
    }

    @Test
    public void d_testChartsButton(){
        new NavigationTest().clickBudget();

        onView(withId(R.id.bud_chart_fab))
                .perform(click());

        onView(withId(R.id.textView2))
                .check(matches(ViewMatchers.isDisplayed()));
    }


//utils
    private void clickTabs(String tabName){
        onView(withText(tabName))
                .perform(click());

        onView(withId(R.id.bud_recyclerView))
                .perform(RecyclerViewActions.scrollToPosition(1));
        Utility.sleepMe(1);
    }

    private void fillTheForm(String category){
        //spinner


        onView(withId(R.id.item_getName))
            .perform(ViewActions.typeText("test"),ViewActions.closeSoftKeyboard());

        onView(withId(R.id.item_getPrice))
                .perform(ViewActions.typeText("50.0"),ViewActions.closeSoftKeyboard());


        onView(withId(R.id.item_getContract))
                .perform(ViewActions.typeText("5"),ViewActions.closeSoftKeyboard());


        onView(withId(R.id.item_getYearlyFee))
                .perform(ViewActions.typeText("60"),ViewActions.closeSoftKeyboard());


        onView(withId(R.id.item_getCancelFee))
                .perform(ViewActions.typeText("80"),ViewActions.closeSoftKeyboard());

        onView(withId(R.id.item_btn_addNew))
                .perform(click());


        clearForm();
    }
    private void clearForm(){

        onView(withId(R.id.item_getName))
                .perform(ViewActions.clearText());

        onView(withId(R.id.item_getPrice))
                .perform(ViewActions.clearText());

        onView(withId(R.id.item_getContract))
                .perform(ViewActions.clearText());

        onView(withId(R.id.item_getYearlyFee))
                .perform(ViewActions.clearText());

        onView(withId(R.id.item_getCancelFee))
                .perform(ViewActions.clearText());

    }

}
