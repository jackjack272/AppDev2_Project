package com.example.appdevproject.Db;




import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.test.core.app.ApplicationProvider;
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
import android.content.Context;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.view.View;

//https://developer.android.com/training/data-storage/room#java
//
@RunWith(AndroidJUnit4.class)
public class DataBaseTests<T> extends RoomDatabase {



    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {


    } //want to be able to test multiple types of objects



}
