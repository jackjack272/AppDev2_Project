package com.example.appdevproject.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appdevproject.Budget.Model.Item;
import com.example.appdevproject.DataBase.Interfaces.Debts;
import com.example.appdevproject.DataBase.Interfaces.Income;
import com.example.appdevproject.DataBase.Interfaces.Items;
import com.example.appdevproject.DataBase.Interfaces.Stock;
import com.example.appdevproject.DataBase.Interfaces.Totals;
import com.example.appdevproject.DataBase.Interfaces.Users;

import com.example.appdevproject.Investment.Models.Invest_Debt;
import com.example.appdevproject.Investment.Models.Invest_Stock;
import com.example.appdevproject.Investment.Models.Totals_Save;
import com.example.appdevproject.Tax.Models.Tax_Income;
import com.example.appdevproject.User.Models.User;

import java.util.ArrayList;
import java.util.List;

public class ProjectDb extends SQLiteOpenHelper
        implements Debts, Items, Users, Totals, Income, Stock

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
        db.execSQL(MAKE_USER_TABLE);
        // Create the Item table
        db.execSQL(MAKE_ITEM_TABLE); //fk user
        //Create a table for Debt.
        db.execSQL(MAKE_DEBT_TABLE); //fk user
        //totals table
        db.execSQL(MAKE_TOTALS_TABLE);//fk usr
        db.execSQL(MAKE_INCOME_TABLE);//fk usr
        db.execSQL(MAKE_STOCK_TABLE); //fk usr

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
        String getOne=String.format("SELECT * FROM %s WHERE %s == %d;", DEBT_TABLE, DEBT_ID, id );

        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor = db.query(
                DEBT_TABLE,                    // Table name
                null,                          // Columns (null means all columns)
                DEBT_ID + "=?",                // Selection (where clause)
                new String[] { String.valueOf(id) },  // Selection arguments
                null,                          // Group by
                null,                          // Having
                null                           // Order by
        );

        int items= cursor.getCount();

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
        String str=String.format("SELECT * FROM %s WHERE %s == %d AND %s == %d ORDER BY %s;",
                DEBT_TABLE, DEBT_FORENKEY,foreignKey, DEBT_ISDEBT, 1, DEBT_AMOUNTBORROWED);

        SQLiteDatabase db  = getReadableDatabase();
        Cursor cursor= db.rawQuery(str,null);
        cursor.moveToFirst();

        List<Invest_Debt> myDebts= new ArrayList<>();
        do{
            if(cursor.getCount()==0){
                break;
            }
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

        }while (cursor.moveToNext());
        return  myDebts;
    }
    public List<Invest_Debt>  debt_readDebt(int foreignKey,int option){
        String str=String.format("SELECT * FROM %s WHERE %s == %d AND %s == %d ORDER BY %s;",
                DEBT_TABLE, DEBT_FORENKEY,foreignKey, DEBT_ISDEBT, 1, DEBT_AMOUNTBORROWED);

        switch (option){
            case 1:
                str=String.format("SELECT * FROM %s WHERE %s == %d AND %s == %d ORDER BY %s %s;",
                        DEBT_TABLE, DEBT_FORENKEY,foreignKey, DEBT_ISDEBT, 1, DEBT_INTERESTRATE,"DESC");
                break;
            case 2:
                break;
            default:
                break;
        }


        SQLiteDatabase db  = getReadableDatabase();

        Cursor cursor= db.rawQuery(str,null);
        cursor.moveToFirst();

        List<Invest_Debt> myDebts= new ArrayList<>();
        do{
            if(cursor.getCount()==0){
                break;
            }

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

        }while (cursor.moveToNext());

        return myDebts;
    }
    public List<Invest_Debt> debt_readBonds(int foreignKey){

        SQLiteDatabase db=getReadableDatabase();
        String str=String.format("SELECT * FROM %s WHERE %s == %d AND %s == %d ORDER BY %s %s; ",
                DEBT_TABLE, DEBT_FORENKEY,foreignKey, DEBT_ISDEBT, 0, DEBT_AMOUNTBORROWED,"DESC");

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
    public void debt_updateOne(Invest_Debt debt){
        ContentValues contentValue = new ContentValues();

        contentValue.put(DEBT_NAME,debt.getDebtName());
        contentValue.put(DEBT_AMOUNTBORROWED,debt.getAmountBorred());
        contentValue.put(DEBT_INTERESTRATE,debt.getInterestRate());
        contentValue.put(DEBT_COMPOUNDSPERYEAR,debt.getCompoundsPerYear());
        contentValue.put(DEBT_LOANTERM,debt.getLoanTermInMonths());

        SQLiteDatabase db= getWritableDatabase();
        db.update(DEBT_TABLE, contentValue,
                this.DEBT_ID+" =?",new String[] {String.valueOf(debt.getId())});

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
            cv.put(USER_PASSWORD,  user.getPassword());
            cv.put(USER_EMAIL, user.getEmail());
            cv.put(USER_DOB, user.getDob());

        db.update(USER_TABLE, cv, USER_ID+"=?", new String[]{String.valueOf(user.getId())} );
    }
//---------------



//Totals
    public List<Totals_Save> totals_readTotal(Integer foreignKey){
        String str=String.format("SELECT %s FROM %s WHERE %s == %d",
                "*",TOTALS_TABLE, TOTALS_FOREIGNKEY, foreignKey);

        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor= db.rawQuery(str, null);
        cursor.moveToFirst();

        List<Totals_Save> myItems= new ArrayList<>();
        String name="";
        for(int i=0; i<cursor.getCount(); i++){

            switch (cursor.getInt(cursor.getColumnIndexOrThrow(TOTALS_ID))){
                case TOTAL_BOND_PK:
                    name="Bond";
                    break;
                case TOTAL_DEBT_PK:
                    name="Debt";
                    break;
                case TOTAL_STOCK_PK:
                    name="Stock";
                    continue; //stock is being dropped from requirements
//                    break;
                default:
                    break;
            }

            myItems.add(new Totals_Save(
                name,
                cursor.getDouble(cursor.getColumnIndexOrThrow(TOTALS_GROWTH)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(TOTALS_AMOUNT)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(TOTALS_INTEREST))
            ));
            cursor.moveToNext();
        }
        return myItems;

    }
    @Override
    public void totals_saveOne(Totals_Save totals_save) {

        ContentValues cv= new ContentValues();

        cv.put(TOTALS_FOREIGNKEY,  totals_save.getForeignKey());
        cv.put(TOTALS_GROWTH,  totals_save.getYearlyGain());
        cv.put(TOTALS_AMOUNT,  totals_save.getTotalAmount());
        cv.put(TOTALS_INTEREST,  totals_save.getYearlyInterestCharge());


        SQLiteDatabase db= getWritableDatabase();
        db.insert(TOTALS_TABLE,null,cv);
    }
    public void totals_update(Totals_Save totals_save){

        ContentValues cv= new ContentValues();

        cv.put(TOTALS_GROWTH,  totals_save.getYearlyGain());
        cv.put(TOTALS_AMOUNT,  totals_save.getTotalAmount());
        cv.put(TOTALS_INTEREST,  totals_save.getYearlyInterestCharge());


        SQLiteDatabase db= getWritableDatabase();
        db.update(TOTALS_TABLE,cv,TOTALS_ID+"=?",new String[]{String.valueOf(totals_save.getId())});
    }
    public Boolean totals_empty(){
        String query=String.format("SELECT %s FROM %s",
                "*", TOTALS_TABLE);

        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor= db.rawQuery(query,null);
        return cursor.getCount()==0;

    }
    @Override
    public Boolean totals_emptyBonds() {
        String query= String.format("SELECT %s FROM %s WHERE %s =%s",
                "*",TOTALS_TABLE, TOTALS_ID,TOTAL_BOND_PK);

        return getReadableDatabase().rawQuery(query,null).getCount()==0;
    }
    @Override
    public Boolean totals_emptyDebts() {
        String query= String.format("SELECT %s FROM %s WHERE %s =%s",
                "*",TOTALS_TABLE, TOTALS_ID,TOTAL_DEBT_PK);

        return getReadableDatabase().rawQuery(query,null).getCount()==0;

    }
    @Override
    public Boolean totals_emptyStocks() {
        String query= String.format("SELECT %s FROM %s WHERE %s =%s",
                "*",TOTALS_TABLE, TOTALS_ID,TOTAL_STOCK_PK);

        return getReadableDatabase().rawQuery(query,null).getCount()==0;
    }
//crud





//Income
    public Boolean income_saveOne(Tax_Income income){
        if(income.getForeignKey()==null){
            return false;
        }
        SQLiteDatabase db= getWritableDatabase();

        ContentValues con= new ContentValues();
            con.put(INCOME_FORENKEY,income.getForeignKey());
            con.put(INCOME_JOBTITLE,income.getJobTitle());
            con.put(INCOME_HOURLYWAGE, income.getHourlyWage());
            con.put(INCOME_BYWEEKLYHOURS,income.getHoursWorked());
            con.put(INCOME_BONUSES, income.getBonuses());

        db.insert(INCOME_TABLE,null, con);
        return true;
    }

    //read one

    //read all
    public List<Tax_Income> income_readAll(Integer foreignKey){
        String sql_query=String
                .format("SELECT %s FROM %s WHERE %s ==%d;",
                    "*", INCOME_TABLE, INCOME_FORENKEY,foreignKey
                );

        SQLiteDatabase db= getReadableDatabase();

        Cursor cursor= db.rawQuery(sql_query, null);
        cursor.moveToFirst();

        List<Tax_Income> myTaxedIncome=new ArrayList<>();
        do{
            if(cursor.getCount()==0){
                return null;
            }
            myTaxedIncome.add(new Tax_Income(
                    cursor.getInt(cursor.getColumnIndexOrThrow(INCOME_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(INCOME_JOBTITLE)),

                    cursor.getDouble(cursor.getColumnIndexOrThrow(INCOME_HOURLYWAGE)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(INCOME_BYWEEKLYHOURS)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(INCOME_BONUSES))
            ));

        }while (cursor.moveToNext());

        return myTaxedIncome;
    }

    //delete



    //update


//Stock

//Create
    public void stock_saveOne(Invest_Stock stock){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put(STOCK_TICKER,stock.getTicker());
        cv.put(STOCK_PRICE,stock.getPrice());

        cv.put(STOCK_QTY, stock.getQuantity());
        cv.put(STOCK_DIV,stock.getDividends());
        cv.put(STOCK_LASTSIXMONTHCLOSE,stock.getLastSixMonthClose());
        cv.put(STOCK_LASTSIXMONTHOPEN,stock.getLastSixMonthOpen());

        cv.put(STOCK_FORENKEY, stock.getForeignKey());

        db.insert(STOCK_TABLE,null,cv);
    }


//read all
    public List<Invest_Stock> stock_readAll(int foreignKey){
        String query=String.format("SELECT %s FROM %s WHERE %s == %d",
                "*",STOCK_TABLE,STOCK_FORENKEY,foreignKey
        );


        SQLiteDatabase db= getReadableDatabase();
        Cursor cu= db.rawQuery(query,null);
        cu.moveToFirst();
        List<Invest_Stock> myStocks= new ArrayList<>();

        do{
            if(cu.getCount()==0){
                return null;
            }

            myStocks.add(new Invest_Stock(
                cu.getInt(cu.getColumnIndexOrThrow(STOCK_ID)),
                foreignKey,
                cu.getString(cu.getColumnIndexOrThrow(STOCK_TICKER)),
                cu.getDouble(cu.getColumnIndexOrThrow(STOCK_PRICE)),
                cu.getDouble(cu.getColumnIndexOrThrow(STOCK_DIV)),
                cu.getDouble(cu.getColumnIndexOrThrow(STOCK_LASTSIXMONTHCLOSE)),
                cu.getDouble(cu.getColumnIndexOrThrow(STOCK_LASTSIXMONTHOPEN)),
                cu.getInt(cu.getColumnIndexOrThrow(STOCK_QTY))
            ));

        }while(cu.moveToNext());

        return  myStocks;
    }







}