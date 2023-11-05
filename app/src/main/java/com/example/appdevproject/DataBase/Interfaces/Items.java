package com.example.appdevproject.DataBase.Interfaces;

import static com.example.appdevproject.DataBase.Interfaces.Users.USER_ID;

import com.example.appdevproject.Budget.Model.Item;

import java.util.List;

public interface Items {

    static final String ITEM_TABLE ="items";
    static final String ITEM_ID="id";
    static final String ITEM_NAME="name";
    static final String ITEM_CATEGORY="category";
    static final String ITEM_FREQUENCYOFPURCHASE="frequency";
    static final String ITEM_PRICE="price";
    static final String ITEM_RENEWALFEE="renewal_fee";
    static final String ITEM_CANCELATIONFEE="cancel_fee";
    static final String ITEM_CONTRACTLEN="contract_len";
    static final String ITEM_FORENKEY= USER_ID+"_foreign";


    public void item_makeOne(Item item);
    public Item item_getOne(int position);
    public List<Item> item_getAll(int userId);
    public void item_update(int id, Item item);
    public void item_remove(int position);
}
