package com.example.appdevproject.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appdevproject.Budget.Model.Item;
import com.example.appdevproject.DataBase.Interfaces.Debts;
import com.example.appdevproject.DataBase.Interfaces.Items;
import com.example.appdevproject.DataBase.Interfaces.Totals;
import com.example.appdevproject.DataBase.Interfaces.Users;

import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.User.User;

import java.util.ArrayList;
import java.util.List;

public class ProjectDb extends SQLiteOpenHelper
        implements Debts, Items, Users, Totals

{
    /** This is the Database for the project
     *  user -> item (one ->many)
     *  user -> investment (one ->many)
     */

    private static final String db_name="fall23_AndroidApp";
    private static final Integer db_version=1;

    public ProjectDb(Context context) {
        super(context, db_name, null , db_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the User table
        db.execSQL(makeUser);

        // Create the Item table
        db.execSQL(makeItem); //fk user
        //Create a table for Debt.
        db.execSQL(makeDebt); //fk user
        //totals table
        db.execSQL(makeToatls);//fk usr


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE);
        onCreate(db);
    }


//DEBT CRUD
    public void debt_makeOne(Invest_Debt debt){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues cv= new ContentValues();
            cv.put(DEBT_NAME, debt.getDebtName());
            cv.put(DEBT_AMOUNTBORROWED, debt.getAmountBorred());
            cv.put(DEBT_INTERESTRATE, debt.getInterestRate());
            cv.put(DEBT_COMPOUNDSPERYEAR, debt.getCompoundsPerYear());
            cv.put(DEBT_LOANTERM, debt.getLoanTermInMonths());


            cv.put(DEBT_ISDEBT,booleanToInt( debt.getIsDebt()));
            cv.put(DEBT_FORENKEY, debt.getForeinKey());
        db.insert(DEBT_TABLE, null, cv);
        db.close();
    }
    private Integer booleanToInt(boolean isDebt){
        if(isDebt){
            return 1;
        }
        return 0;
    }

    public Invest_Debt debt_readOne(int id){
        String getOne=String.format("SELECT * FROM %s WHERE %s == %d",
                DEBT_TABLE, DEBT_ID, id );

        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor= db.rawQuery(getOne,null);
//        cursor.moveToFirst();

         return new Invest_Debt(
                cursor.getInt(cursor.getColumnIndexOrThrow(DEBT_ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(DEBT_NAME)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(DEBT_AMOUNTBORROWED)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(DEBT_INTERESTRATE)),
                cursor.getInt(cursor.getColumnIndexOrThrow(DEBT_COMPOUNDSPERYEAR)),
                cursor.getInt(cursor.getColumnIndexOrThrow(DEBT_LOANTERM)),
                cursor.getInt(cursor.getColumnIndexOrThrow(DEBT_ISDEBT))
        );

    }
    public List<Invest_Debt>  debt_readDebt(int foreignKey){
        String getAllSql=String
    .format("SELECT %s FROM %s WHERE %s == %d ORDER BY amount_borrowed ",
            "*",DEBT_TABLE, DEBT_FORENKEY, foreignKey);


        SQLiteDatabase db  = getReadableDatabase();

        Cursor cursor= db.rawQuery(getAllSql,null);
        cursor.moveToFirst();

        List<Invest_Debt> myDebts= new ArrayList<>();

        while (cursor.moveToNext()){
            Invest_Debt debt=new Invest_Debt(
                    cursor.getInt(cursor.getColumnIndexOrThrow(DEBT_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DEBT_NAME)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(DEBT_AMOUNTBORROWED)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(DEBT_INTERESTRATE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DEBT_COMPOUNDSPERYEAR)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DEBT_LOANTERM)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DEBT_ISDEBT))
            );
            myDebts.add(debt);
        }
        return myDebts;
    }
    public List<Invest_Debt> debt_readBonds(int foreignKey){

        SQLiteDatabase db=getReadableDatabase();
        String str=String.format("SELECT * FROM %s WHERE %s == %d AND %s == %d;",
                DEBT_TABLE, DEBT_FORENKEY,foreignKey, DEBT_ISDEBT, 1);

        Cursor cursor= db.rawQuery(str,null);
        cursor.moveToFirst();

        List<Invest_Debt> myDebts= new ArrayList<>();
        while (true){//since there is 1 item, it will fail to get next thus skip the first parse
            if(cursor.getCount() <=0){
                break;
            }

            myDebts.add(
                    new Invest_Debt(
                    cursor.getInt(cursor.getColumnIndexOrThrow(DEBT_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DEBT_NAME)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(DEBT_AMOUNTBORROWED)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(DEBT_INTERESTRATE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DEBT_COMPOUNDSPERYEAR)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DEBT_LOANTERM)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DEBT_ISDEBT))
                    )
            );

            if(! cursor.moveToNext()){
                break;
            }
        }

        return myDebts ;
    }
    public void debt_updateOne(int position, Invest_Debt debt){
        ContentValues contentValue = new ContentValues();

        contentValue.put(DEBT_AMOUNTBORROWED,debt.getAmountBorred());
        contentValue.put(DEBT_INTERESTRATE,debt.getInterestRate());
        contentValue.put(DEBT_COMPOUNDSPERYEAR,debt.getCompoundsPerYear());
        contentValue.put(DEBT_LOANTERM,debt.getLoanTermInMonths());

        getWritableDatabase()
                .update(DEBT_TABLE, contentValue, this.DEBT_ID+" =?",
                        new String[] {String.valueOf(debt.getId())});
    }
    public void debt_deleteOne(int position){
        getWritableDatabase().delete(DEBT_TABLE,DEBT_ID+"==?",new String[]{String.valueOf(position)});
    }
//----------


//ITEM CRUD
    public void item_makeOne(Item item){
        SQLiteDatabase db= getWritableDatabase();

        ContentValues cv= new ContentValues();
            cv.put(ITEM_NAME,item.getNameOfItem()  );
            cv.put(ITEM_CATEGORY,item.getCategory()  );
//            cv.put(ITEM_FREQUENCYOFPURCHASE,item.getFrequencyOfPurchase()  );
            cv.put(ITEM_PRICE,item.getPriceOfItem()  );
            cv.put(ITEM_RENEWALFEE,item.getYearlyRenewalFee()  );
            cv.put(ITEM_CANCELATIONFEE,item.getCancelationFee()  );
            cv.put(ITEM_CONTRACTLEN,item.getContractLength()  );
            cv.put(ITEM_FORENKEY, item.getForenKey());

        db.insert(ITEM_TABLE, null, cv);
        db.close();
    }
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
                cu.getInt(cu.getColumnIndexOrThrow(USER_ID))
        );

//                cu.getInt(cu.getColumnIndexOrThrow(ITEM_FREQUENCYOFPURCHASE)),
        db.close();
        return item;
    }
    public List<Item> item_getAll(int userId){



        String getAll=String.format("SELECT %s FROM %s WHERE %s == %d",
                "*", ITEM_TABLE , ITEM_FORENKEY, userId);

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
                    cu.getDouble(cu.getColumnIndexOrThrow(ITEM_PRICE)),
                    cu.getDouble(cu.getColumnIndexOrThrow(ITEM_RENEWALFEE)),
                    cu.getDouble(cu.getColumnIndexOrThrow(ITEM_CANCELATIONFEE)),
                    cu.getInt(cu.getColumnIndexOrThrow(ITEM_CONTRACTLEN)),
                    cu.getInt(cu.getColumnIndexOrThrow(ITEM_FORENKEY))
                )
            );
        }
        db.close();

        return items;
    }
    public void item_update(int id, Item item){


    }
    public void item_remove(int position){
        SQLiteDatabase db= getWritableDatabase();
//        db.delete(ITEM_TABLE, ITEM_ID+"== ",)
        //dont remeber the code for this
    }
//---------------



// USER CRUD
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
    public User getUserByUsername(String _username){
        // this needs to be parameretised- injection attack
        String getUser = String.format("SELECT %s, %s, %s, %s, %s FROM %s WHERE "
                        + this.USER_USERNAME + " = '%s';"
                , USER_ID, USER_USERNAME, USER_PASSWORD, USER_EMAIL, USER_DOB, USER_TABLE, _username);

        // i dont want to take out the password
            //wierd spacing to see the parameters better
        User x=null;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.rawQuery(getUser,null);

        if (cursor.getCount() ==1){
            //if there is a user with this user name return them.
            cursor.moveToFirst();
            x = new User(
                    cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)), // this causes errors
                    cursor.getString(cursor.getColumnIndexOrThrow(USER_USERNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(USER_PASSWORD)),
                    cursor.getString(cursor.getColumnIndexOrThrow(USER_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(USER_DOB))
            );
            // i think i was closing the db before i was done with it.
            db.close();
        }
        return x;
        //id will be in shared preferences for the logged in user so dont need it here.
    }
    public int getUserById(String username){
        String sql= String.format("SELECT %s FROM %s WHERE %s == '%s'; ",USER_ID, USER_TABLE, USER_USERNAME, username);

        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();

        Integer userId= cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID));

        return userId;


//      Cursor cursor= (getReadableDatabase()).rawQuery(sql,null) ;
//      return cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID));
//        SELECT id FROM user WHERE username == James James
    }
    public void updateUser(User user){
        SQLiteDatabase db= getWritableDatabase();

        ContentValues cv = new ContentValues();
            cv.put(USER_PASSWORD, user.getPassword());
            cv.put(USER_EMAIL, user.getEmail());
            cv.put(USER_DOB, user.getDob());

        db.update(USER_TABLE, cv, this.USER_USERNAME+" =?", new String[] {String.valueOf(user.getUserName())});
    }
//---------------



//Totals


    public void saveTotal(Totals totals){

    }
    public List<Totals> readTotal(){
        return null;
    };
    public void updateTotal(Totals totals){

    };





//crud




}