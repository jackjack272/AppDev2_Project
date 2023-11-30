package com.example.appdevproject.RegistrationAndNav;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.appdevproject.DataBase.ProjectDb;
import com.example.appdevproject.R;
import com.example.appdevproject.User.Models.User;
import com.example.appdevproject.User.Registration_Page;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

//import org.mockito.Mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import android.content.Context;

//https://www.youtube.com/watch?v=sDp8JNbITm4&list=PLx5ipGeoOO2hCbqjX784_I4Y4mDsVbVEf&index=2
@RunWith(AndroidJUnit4.class)

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RegistrationTests {
    /**
     *  The application is using the user's id as the FK.
     *      all tests need to extend registration Test since need to check if user in db
     *
     *
     * Test questions:
     *  Can the user fill out registration form?
     *
     *  Is the user added to the DB?
     *
     *  can the user login?
     *
     *
     */

    private ProjectDb projectDb;

    @Rule
    public ActivityTestRule <Registration_Page> myact= new ActivityTestRule <>(Registration_Page.class);

    @Test //can only do it once, then the user is in the db.
    public void a_testRegister() {

        //fill the form.
        Espresso.onView(ViewMatchers.withId(R.id.getUserName))
                .perform(ViewActions.typeText("james smith"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.getPassword))
                .perform(ViewActions.typeText("apple2"),
                        ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.getEmail))
                .perform(ViewActions.typeText("JohnDoe@hotmail.com"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.getDateOfBirth))
                .perform(ViewActions.typeText("04/5/2023"), ViewActions.closeSoftKeyboard());


        //click register
        Espresso.onView(ViewMatchers.withId(R.id.nextPage))
                .perform(ViewActions.click());

        //see if the next page has the required username
        Espresso.onView(ViewMatchers.withId(R.id.textUserName))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Context context = ApplicationProvider.getApplicationContext();
        projectDb = new ProjectDb(context);
        if (null == projectDb.getUserByUsername("james smith")) {
            fail("user was not found in db. ");
        }

//        int userId=projectDb.getUserById( projectDb.getUserByUsername("james smith").getUserName());

    }

    @Test
    public void b_testLogIn() {

        //toggle the switch
        Espresso.onView(ViewMatchers.withId(R.id.switch1))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.isNotChecked()))
                .perform(ViewActions.click())
                .check(ViewAssertions.matches(ViewMatchers.isChecked()));

        //removed other fields

        Espresso.onView(ViewMatchers.withId(R.id.getDateOfBirth))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

        Espresso.onView(ViewMatchers.withId(R.id.getEmail))
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));


        //fill the form.
        Espresso.onView(ViewMatchers.withId(R.id.getUserName))
                .perform(ViewActions.typeText("james smith"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.getPassword))
                .perform(ViewActions.typeText("apple2"),
                        ViewActions.closeSoftKeyboard());


        //click register
        Espresso.onView(ViewMatchers.withId(R.id.nextPage))
                .perform(ViewActions.click());


        //see if the next page has the required username

        Espresso.onView(ViewMatchers.withId(R.id.textUserName))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

//        removeUser();

    }

    private void removeUser(){
        Context context = ApplicationProvider.getApplicationContext();

        projectDb = new ProjectDb(context);
        projectDb.user_delete("james smith");
    }
}
