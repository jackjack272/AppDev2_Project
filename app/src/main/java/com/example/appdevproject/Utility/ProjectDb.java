package com.example.appdevproject.Utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appdevproject.Budget.Item;
import com.example.appdevproject.User.User;

import java.util.ArrayList;
import java.util.List;

public class ProjectDb extends SQLiteOpenHelper {
    //db info
        private static final String db_name="fall23_AndroidApp";
            // how do i put more tables into this db?
            // if i make a new db that extends SQLiteOpenHelper ill need to give it a new name...
        private static final Integer db_version=1;



//data memebers for Budget..
        private static final String USER_TABLE ="user";
        private static final String USER_ID ="id";
        private static final String USER_USERNAME="username";
        private static final String USER_PASSWORD ="password";
        private static final String USER_EMAIL ="email";
        private static final String USER_DOB ="DateOfBirth";

//--------------------

//  Data members of Item
        private static final String ITEM_TABLE ="items";
        private static final String ITEM_ID="id";
        private static final String ITEM_NAME="name";
        private static final String ITEM_CATEGORY="category";
        private static final String ITEM_FREQUENCYOFPURCHASE="frequency";
        private static final String ITEM_PRICE="price";
        private static final String ITEM_RENEWALFEE="renewal_fee";
        private static final String ITEM_CANCELATIONFEE="cancel_fee";
        private static final String ITEM_CONTRACTLEN="contract_len";
        private static final String ITEM_FOREIGN_KEY="PersonId";
//---------------


    public ProjectDb(Context context) {
        super(context, db_name, null , db_version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String makeUser="CREATE TABLE "+ USER_TABLE
            +"("
                + USER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +USER_USERNAME+" TEXT,"
                + USER_PASSWORD +" TEXT,"
                + USER_EMAIL +" TEXT,"
                + USER_DOB +" String"
            +")";
        db.execSQL(makeUser);
        // if i close the db here it crash the project


    //table linked to user
        String makeItem="CREATE TABLE "+ITEM_TABLE
                +"(" +
                ITEM_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ITEM_NAME +" TEXT," +
                ITEM_CATEGORY +" INTEGER, "+
                ITEM_FREQUENCYOFPURCHASE +" INTEGER, "+
                ITEM_PRICE+" REAL, "+
                ITEM_RENEWALFEE+" REAL,"+
                ITEM_CANCELATIONFEE+" REAL,"+
                ITEM_CONTRACTLEN+" INTEGER, "+
                "FOREIGN KEY ("+ITEM_FOREIGN_KEY+") REFERENCES "+ USER_TABLE +"("+ USER_ID +" )"
                    // this references user table and uses its id.
                +");";

        db.execSQL(makeItem);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE);
        onCreate(db);
    }



//ITEM CRUD

    //create one
    public void item_makeOne(Item item){
        SQLiteDatabase db= getWritableDatabase();

        ContentValues cv= new ContentValues();
            cv.put(ITEM_NAME,item.getNameOfItem()  );
            cv.put(ITEM_CATEGORY,item.getCategory()  );
            cv.put(ITEM_FREQUENCYOFPURCHASE,item.getFrequencyOfPurchase()  );
            cv.put(ITEM_PRICE,item.getPriceOfItem()  );
            cv.put(ITEM_RENEWALFEE,item.getYearlyRenewalFee()  );
            cv.put(ITEM_CANCELATIONFEE,item.getCancelationFee()  );
            cv.put(ITEM_CONTRACTLEN,item.getContractLength()  );
            cv.put(ITEM_FOREIGN_KEY, item.getForenKey());

        db.insert(ITEM_TABLE, null, cv);
        db.close();
    }

    //read one
    public Item item_getOne(int position){
        String getById= "SELECT * FROM "+ITEM_TABLE +" WHERE "+ USER_ID +" == "+position;

        SQLiteDatabase db= getReadableDatabase();

        Cursor cu= db.rawQuery(getById, null);
        Item item= new Item(
                cu.getInt(cu.getColumnIndexOrThrow(ITEM_ID)),
                cu.getString(cu.getColumnIndexOrThrow(ITEM_NAME)),
                cu.getInt(cu.getColumnIndexOrThrow(ITEM_CATEGORY)),

                cu.getDouble(cu.getColumnIndexOrThrow(ITEM_PRICE)),
                cu.getDouble(cu.getColumnIndexOrThrow(ITEM_RENEWALFEE)),
                cu.getDouble(cu.getColumnIndexOrThrow(ITEM_CANCELATIONFEE)),
                cu.getInt(cu.getColumnIndexOrThrow(ITEM_CONTRACTLEN)),
                cu.getInt(cu.getColumnIndexOrThrow(ITEM_FOREIGN_KEY))
        );

//                cu.getInt(cu.getColumnIndexOrThrow(ITEM_FREQUENCYOFPURCHASE)),
        db.close();
        return item;
    }
    //read all
    public List<Item> item_getAll(int userId){
        String getAll= "SELECT * FROM "+ITEM_TABLE+" WHERE "+ITEM_FOREIGN_KEY+" == "+userId;

        SQLiteDatabase db= getReadableDatabase();

        Cursor cu= db.rawQuery(getAll, null);
        cu.moveToFirst();

        List<Item> items= new ArrayList<>();
        while(cu.moveToNext()){
            items.add(
                new Item(
                    cu.getInt(cu.getColumnIndexOrThrow(ITEM_ID)),
                    cu.getString(cu.getColumnIndexOrThrow(ITEM_NAME)),
                    cu.getInt(cu.getColumnIndexOrThrow(ITEM_CATEGORY)),
                    cu.getInt(cu.getColumnIndexOrThrow(ITEM_FREQUENCYOFPURCHASE)),
                    cu.getDouble(cu.getColumnIndexOrThrow(ITEM_PRICE)),
                    cu.getDouble(cu.getColumnIndexOrThrow(ITEM_RENEWALFEE)),
                    cu.getDouble(cu.getColumnIndexOrThrow(ITEM_CANCELATIONFEE)),
                    cu.getInt(cu.getColumnIndexOrThrow(ITEM_CONTRACTLEN))
                )
            );
        }
        db.close();

        return items;
    }

    //update one
    public void item_update(int id, Item item){


    }


    //remove one
    public void item_remove(int position){
        SQLiteDatabase db= getWritableDatabase();
//        db.delete(ITEM_TABLE, ITEM_ID+"== ",)
        //dont remeber the code for this
    }

//---------------



// USER CRUD

    //Create
    public long makeUser(User user){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
            cv.put(USER_USERNAME, user.getUserName());
            cv.put(USER_PASSWORD, user.getPassword());
            cv.put(USER_EMAIL, user.getEmail());
            cv.put(USER_DOB, user.getDob());

        long x=db.insert(USER_TABLE, null, cv);
        db.close();

        return x;
    }

    // Read one
    public User getUserByUsername(String _username){
        // this needs to be parameretised- injection attack
        String getUser = String.format("SELECT %s, %s, %s, %s, %s FROM %s WHERE " + this.USER_USERNAME + " = '%s';"
                , USER_ID, USER_USERNAME, USER_PASSWORD, USER_EMAIL, USER_DOB, USER_TABLE, _username);

        // i dont want to take out the password
            //wierd spacing to see the parameters better

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.rawQuery(getUser,null);


        User x= new User(
                cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)), // this causes errors
                cursor.getString(cursor.getColumnIndexOrThrow(USER_USERNAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(USER_PASSWORD)),
                cursor.getString(cursor.getColumnIndexOrThrow(USER_EMAIL)),
                cursor.getString(cursor.getColumnIndexOrThrow(USER_DOB))
        );

        // i think i was closing the db before i was done with it.
        db.close();
        return x;
        //id will be in shared preferences for the logged in user so dont need it here.
    }


    public int getUserById(String username){
        String sql= "SELECT "+ USER_ID +" FROM "+ USER_TABLE +" WHERE "+USER_USERNAME+" == "+username;
        Cursor cursor= (getReadableDatabase()).rawQuery(sql,null) ;
        return cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID));
    }


    //update one
    public void updateUser(User user){
        SQLiteDatabase db= getWritableDatabase();

        ContentValues cv = new ContentValues();
            cv.put(USER_PASSWORD, user.getPassword());
            cv.put(USER_EMAIL, user.getEmail());
            cv.put(USER_DOB, user.getDob());

        db.update(USER_TABLE, cv, this.USER_USERNAME+" =?", new String[] {String.valueOf(user.getUserName())});
    }

    //delete one

//---------------


}