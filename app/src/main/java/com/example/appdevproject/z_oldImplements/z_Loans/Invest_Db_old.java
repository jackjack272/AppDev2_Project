package com.example.appdevproject.z_oldImplements.z_Loans;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Invest_Db_old extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "temp_db";
    private static final String TABLE_Investment = "investment";
    private static final String KEY_ID = "id";
    private static final String KEY_USER_ID = "userid";
    private static final String KEY_NAME = "investname";
    private static final String KEY_TYPE = "investtype";
    private static final String KEY_AMOUNT = "investamount";

    public Invest_Db_old(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    public Invest_Db_old(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Investment + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USER_ID + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_TYPE + " TEXT,"
                + KEY_AMOUNT + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Investment);
        // Create tables again
        onCreate(db);
    }

    // Adding new Investment
    void insertInvestments(String userid, String investname, String investtype, String investamount){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_USER_ID, userid);
        cValues.put(KEY_NAME, investname);
        cValues.put(KEY_TYPE, investtype);
        cValues.put(KEY_AMOUNT, investamount);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Investment,null, cValues);
        db.close();
    }

    // Get Investments
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetInvestments(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> investmentList = new ArrayList<>();
        String query = "SELECT userid, investname, investtype, investamount FROM " + TABLE_Investment;

        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> investment = new HashMap<>();
            investment.put("userid",cursor.getString(cursor.getColumnIndex(KEY_USER_ID)));
            investment.put("investname",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            investment.put("investtype",cursor.getString(cursor.getColumnIndex(KEY_TYPE)));
            investment.put("investamount",cursor.getString(cursor.getColumnIndex(KEY_AMOUNT)));
            investmentList.add(investment);
        }
        return  investmentList;
    }
}