package com.example.appdevproject.DataBase.Interfaces;

import com.example.appdevproject.User.User;

public interface Users {
    static final String USER_TABLE ="user";
    static final String USER_ID ="id";
    static final String USER_USERNAME="username";
    static final String USER_PASSWORD ="password";
    static final String USER_EMAIL ="email";
    static final String USER_DOB ="DateOfBirth";


    public long makeUser(User user);
    public User getUserByUsername(String _username);
    public int getUserById(String username);
    public void updateUser(User user);


}
