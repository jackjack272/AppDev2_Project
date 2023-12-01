package com.example.appdevproject.Pages;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.R;
import com.example.appdevproject.RegistrationAndNav.NavigationTest;
import com.example.appdevproject.User.Registration_Page;
import com.example.appdevproject.Utility.Utility;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import okhttp3.internal.Util;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Budget {

    @Rule
    public ActivityTestRule <Registration_Page> myact= new ActivityTestRule<>(Registration_Page.class);


    @Test
    public void a_testAddItem(){
        new NavigationTest().clickBudget();
        //from string: item_category

        fillTheForm("Utility");
        fillTheForm("Transportation");
        fillTheForm("Food");
        fillTheForm("Entertainment");
        fillTheForm("Housing");
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
        //housing is the page that it opens on so it might be causing me issues.


        clickTabs("Utility");
        clickTabs("Food");
        clickTabs("Transport");
        clickTabs("Entertain");
        clickTabs("Housing"); //added time delay in method to see actions
    }

    @Test
    public void d_testChartsButton(){
        new NavigationTest().clickBudget();

        onView(withId(R.id.bud_chart_fab))
                .perform(click());

        onView(withId(R.id.textView2))
                .check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void e_testDeleteCards(){
        new NavigationTest().clickBudget();
        clickTabsAndDeleteItem("Food");
        clickTabsAndDeleteItem("Utility");
        clickTabsAndDeleteItem("Food");
        clickTabsAndDeleteItem("Transport");
        clickTabsAndDeleteItem("Entertain");
        clickTabsAndDeleteItem("Housing");
    }

    @Test
    public void f_testDeleteItems(){
        Context context = ApplicationProvider.getApplicationContext();
        ProjectDb projectDb = new ProjectDb(context);
        int user_id= projectDb.getUserByUsername("james smith").getId();

        if(0!=projectDb.item_getAll(user_id).size()){
            fail("budget items not in db ");
        }

    }


    @Test
    public void g_testChartsButton(){
        //this will test if the breakdown has changed after
        //deleting item.
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

    private void clickTabsAndDeleteItem(String tabName){
        this.clickTabs(tabName);

        Espresso.onView(ViewMatchers.withId(R.id.bud_recyclerView))
                .perform(RecyclerViewActions.scrollToPosition(0));

        Espresso.onView(ViewMatchers.withId(R.id.imgDelete))
                .perform(ViewActions.click());

    }


    private void fillTheForm(String category){
        //click the fab button
        onView(withId(R.id.bud_fab))
                .perform(click());


        //name
        onView(withId(R.id.item_getName))
            .perform(ViewActions.typeText("test"),ViewActions.closeSoftKeyboard());

        //price
        onView(withId(R.id.item_getPrice))
                .perform(ViewActions.typeText("50.0"),ViewActions.closeSoftKeyboard());


        onView(withId(R.id.item_getContract))
                .perform(ViewActions.typeText("5"),ViewActions.closeSoftKeyboard());


        onView(withId(R.id.item_getYearlyFee))
                .perform(ViewActions.typeText("60"),ViewActions.closeSoftKeyboard());


        onView(withId(R.id.item_getCancelFee))
                .perform(ViewActions.typeText("80"),ViewActions.closeSoftKeyboard());

        //spinner
        onData(allOf(is(instanceOf(String.class)), is(category)))
                .perform();
            //this works now


        onView(withId(R.id.item_btn_addNew))
                .perform(click());

    }

}
