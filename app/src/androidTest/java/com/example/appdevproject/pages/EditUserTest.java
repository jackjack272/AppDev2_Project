package com.example.appdevproject.pages;

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

import static org.junit.Assert.assertNotNull;


import android.content.ComponentName;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.view.View;

//https://www.youtube.com/watch?v=sDp8JNbITm4&list=PLx5ipGeoOO2hCbqjX784_I4Y4mDsVbVEf&index=2
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EditUserTest {
}
