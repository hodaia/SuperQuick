package com.example.androidb.superquick.General;

import com.example.androidb.superquick.entities.Product;
import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ShoppingList;
import com.example.androidb.superquick.entities.User;
import com.example.androidb.superquick.entities.Users;
import com.parse.ParseQuery;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSessionData {
    private static UserSessionData userSessionData=null;
    public Users user;
    public List<ProductInShoppingList> userShoppingListContent;
    public ShoppingList userShoppingList;
    public int userCurrentShoppingListId;
    public static UserSessionData getInstance() {
        if (userSessionData == null) {
            userSessionData = new UserSessionData();
        }
        return userSessionData;
    }
    public static boolean instanceExist() {
        if (userSessionData == null) {
           return false;
        }
        return true;
    }
    public static void newUserList() {
        Date now=new Date();
        int lastShoppingList;
        //call the function for the last shoppingListId
        lastShoppingList=ShoppingList.getLastShoppingList();
        userSessionData.userShoppingList=new ShoppingList(lastShoppingList+1,"shoppingList",UserSessionData.getInstance().user.getUserId(), now);

    }
    public static void newUserListContent() {
        userSessionData.userShoppingListContent=new ArrayList<>();

    }
    public static void setInstance(Users user) {
        userSessionData = new UserSessionData();
        userSessionData.user = user;
    }

    public static void emptyNewShoppingList() {
       userSessionData.userShoppingListContent=null;
    }


}
