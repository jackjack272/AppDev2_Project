package com.example.appdevproject.DataBase.Interfaces;

import static com.example.appdevproject.DataBase.Interfaces.Users.USER_ID;
import static com.example.appdevproject.DataBase.Interfaces.Users.USER_TABLE;

import com.example.appdevproject.Tax.Models.Tax_Income;

import java.util.List;

public interface Income {

    static final String INCOME_TABLE="income";
    static final String INCOME_ID="id";
    static final String INCOME_FORENKEY=USER_ID+"_foreign";
    static final String INCOME_JOBTITLE="job_title";
    static final String INCOME_HOURLYWAGE="hourly_wage";
    static final String INCOME_BYWEEKLYHOURS="byweekly_worked";
    static final String INCOME_BONUSES="yearly_bonuses";

    static final String MAKE_INCOME_TABLE = "CREATE TABLE "+INCOME_TABLE+" ("
            + INCOME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + INCOME_JOBTITLE +" TEXT,"

            + INCOME_HOURLYWAGE +" REAL,"
            + INCOME_BYWEEKLYHOURS+" REAL,"
            + INCOME_BONUSES +" REAL,"
            +INCOME_FORENKEY+" INTEGER,"
            +"FOREIGN KEY ("+USER_ID+") REFERENCES "+USER_TABLE+" ("+USER_ID+")" +
            ");" ;



    public Boolean income_saveOne(Tax_Income income);
    public List<Tax_Income> income_readAll(Integer foreignKey);







}
