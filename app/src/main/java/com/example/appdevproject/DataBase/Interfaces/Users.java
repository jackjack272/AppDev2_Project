package com.example.appdevproject.DataBase.Interfaces;

import com.example.appdevproject.User.Models.User;

public interface Users {
    static final String USER_TABLE ="user";
    static final String USER_ID ="id";
    static final String USER_USERNAME="username";
    static final String USER_PASSWORD ="password";
    static final String USER_EMAIL ="email";
    static final String USER_DOB ="DateOfBirth";



    static final String MAKE_USER_TABLE = "CREATE TABLE " + USER_TABLE
            + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_USERNAME + " TEXT,"
            + USER_PASSWORD + " TEXT,"
            + USER_EMAIL + " TEXT,"
            + USER_DOB + " TEXT"  // Change "String" to "TEXT"
            + ")";

    public long makeUser(User user);
    public void user_delete(String username);

    public User getUserByUsername(String _username);
    public int getUserById(String username);
    public void updateUser(User user);


}
