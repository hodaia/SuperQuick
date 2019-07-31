package com.example.androidb.superquick.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.androidb.superquick.General.UserSessionData;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseQuery;

@ParseClassName("ShoppingList")
public class ShoppingList extends ParseObject {

    private int shoppingListId;
    private String shoppingListName;
    private int shoppingList_UserId;
    private Date shoppingListDate;

    public ShoppingList() {

    }

    public ShoppingList(int shoppingListId, String shoppingListName, int shoppingList_UserId, Date shoppingListDate) {

        setShoppingListId(shoppingListId);
        setShoppingListName(shoppingListName);
        setShoppingList_UserId(shoppingList_UserId);
        setShoppingListDate(shoppingListDate);
    }

    public int getShoppingListId() {
        return getInt("shoppingListId");
    }

    public void setShoppingListId(int shoppingListId) {
        put("shoppingListId", shoppingListId);
    }

    public String getShoppingListName() {
        return getString("shoppingListName");
    }

    public void setShoppingListName(String shoppingListName) {
        put("shoppingListName", shoppingListName);
    }

    public int getShoppingList_UserId() {
        return getInt("shoppingList_UserId");
    }

    public void setShoppingList_UserId(int shoppingList_UserId) {
        put("shoppingList_UserId", shoppingList_UserId);
    }

    public Date getShoppingListDate() {
        return getDate("shoppingListDate");
    }

    public void setShoppingListDate(Date shoppingListDate) {
        put("shoppingListDate", shoppingListDate);
    }


    //shoppingList Queries
    public static List<ShoppingList> getShoppingListByUserId() {
        List<ShoppingList> parsedShoppingList = new ArrayList<>();
        ParseQuery<ShoppingList> queryShoppingLists = ParseQuery.getQuery("ShoppingList");
        queryShoppingLists.whereEqualTo("shoppingList_UserId", UserSessionData.getInstance().user.getUserId());
        try {
            parsedShoppingList = queryShoppingLists.find();
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return parsedShoppingList;
    }

    public static int getLastShoppingList(){
        ShoppingList lastShoppingList = null;
        ParseQuery<ShoppingList> getShoppingListById = ParseQuery.getQuery("ShoppingList");
        getShoppingListById.orderByDescending("shoppingListId");
        try {
            lastShoppingList = getShoppingListById.getFirst();
        } catch (Exception e) {
        }
        if(lastShoppingList==null)
            return 0;
        return  lastShoppingList.getShoppingListId();
    }
}
