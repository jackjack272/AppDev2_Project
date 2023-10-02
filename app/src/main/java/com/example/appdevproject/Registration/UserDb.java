package com.example.appdevproject.Registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDb extends SQLiteOpenHelper {
    //db info
        private static final String db_name="fall23_AndroidApp";
            // how do i put more tables into this db?
            // if i make a new db that extends SQLiteOpenHelper ill need to give it a new name...
        private static final String table_name="user";
        private static final Integer db_version=1;

    //data members for this table.
        private static final String id="id";
        private static final String username="username";
        private static final String password="password";
        private static final String email="email";
        private static final String date_of_birth="DateOfBirth";



    public UserDb(Context context) {
        super(context, db_name, null , db_version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String make_table="CREATE TABLE "+table_name
            +"("
                +id+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +username+" TEXT,"
                +password+" TEXT,"
                +email +" TEXT,"
                +date_of_birth+" String"
            +")";

        db.execSQL(make_table);
        // if i close the db here it crash the project

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate(db);
    }

    // CRUD

//Create
    public long makeUser(User user){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
            cv.put(username, user.getUserName());
            cv.put(password, user.getPassword());
            cv.put(email, user.getEmail());
            cv.put(date_of_birth, user.getDob());

        long x=db.insert(table_name, null, cv);
        db.close();

        return x;
    }

//Read one
    public User getUserByUsername(String _username){

        // this needs to be parameretised- injection attack
        String getUser = String.format("SELECT %s, %s, %s FROM %s WHERE " + this.username + " = '%s';", id, email, date_of_birth, table_name, _username);

        // i dont want to take out the password
            //wierd spacing to see the parameters better

        SQLiteDatabase db= this.getReadableDatabase();
//        Cursor cursor= db.rawQuery(getUser,null);

        Cursor cursor= db.rawQuery(getUser , null);

        User x= new User(
                cursor.getString(cursor.getColumnIndexOrThrow(id)),
                cursor.getString(cursor.getColumnIndexOrThrow(username)),
                cursor.getString(cursor.getColumnIndexOrThrow(email)),
                cursor.getString(cursor.getColumnIndexOrThrow(date_of_birth))
        );

        // i think i was closing the db before i was done with it.
        db.close();
        return x;
        //id will be in shared preferences for the logged in user so dont need it here.
    }





}