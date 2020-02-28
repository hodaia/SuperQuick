package com.example.androidb.superquick.General;

import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.example.androidb.superquick.entities.ProductInShoppingList;
import com.example.androidb.superquick.entities.ShoppingList;
import com.example.androidb.superquick.entities.Super;
import com.example.androidb.superquick.entities.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserSessionData {
    public  List<Super> superstotalPrice;
    private static UserSessionData userSessionData=null;
    public Users user;
    public List<ProductInShoppingList> userShoppingListContent;
    private List<ProductInShoppingList> productInShoppingLists;
    public ShoppingList userShoppingList;
    public  boolean erase=false;
    // not asking the city and showing relevant supers
    public int userCityId;
    public int userCurrentShoppingListId;

    //for
    public int chosenSuperId;

    //for saved maps
    public int mapShoopingListId;

    public static UserSessionData getInstance() {
        if (userSessionData == null) {
            userSessionData = new UserSessionData();
        }
        return userSessionData;

    }
    public static void setTotalPrice(int position,int totalPrice) {
        userSessionData.superstotalPrice.get(position).totalPrice=totalPrice;

    }
    public static int getTotalPrice(int position) {
       return userSessionData.superstotalPrice.get(position).totalPrice;

    }
    public static boolean instanceExist() {
        if (userSessionData == null) {
           return false;
        }
        return true;
    }
    public static int newUserList() {
        Date now=new Date();
        int lastShoppingList;
        //call the function for the last shoppingListId
        lastShoppingList=ShoppingList.getLastShoppingList();
        userSessionData.userShoppingList=new ShoppingList(lastShoppingList+1,"shoppingList",UserSessionData.getInstance().user.getUserId(), now);
        return lastShoppingList+1;
    }

   public static void eraseListOrMap(){
        userSessionData.erase=true;
   }
    public static boolean getErase(){
       return userSessionData.erase;
    }
    public static void newUserListContent() {
        userSessionData.userShoppingListContent=new ArrayList<>();
    }
    public static void setInstance(Users user) {
        userSessionData = new UserSessionData();
        userSessionData.user = user;
        userSessionData.userCityId=user.getUser_cityId();
    }

    public static void emptyNewShoppingList() {
       userSessionData.userShoppingListContent=null;
    }

    public static void ChosenSuper(int superId) {
        userSessionData.chosenSuperId=superId;
    }
    public static void createAlertDialog(int message, int title, FragmentActivity getActivity) {
        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(getActivity);

        // Set the message show for the Alert time
        builder.setMessage(message);

        // Set Alert Title
        builder.setTitle(title);

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder
                .setPositiveButton(
                        "ok",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                // When the user click yes button
                                // then app will close
                                dialog.cancel();
                            }
                        });


        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();


    }
    public static void createAlertDialog(String message,int  title, String topic, FragmentActivity getActivity) {
        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(getActivity);

        // Set the message show for the Alert time
        String c=topic+message;
        builder.setMessage(c);
        // Set Alert Title
        builder.setTitle(title);

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder
                .setPositiveButton(
                        "מחק",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                UserSessionData.eraseListOrMap();
                                // When the user click yes button
                                // then app will close
                                dialog.cancel();

                            }
                        });
        builder
                .setNegativeButton(
                        "בטל",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // When he user click yes button
                                // then app will close
                                dialog.cancel();

                            }
                        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }

    public List<ProductInShoppingList> getProductInShoppingLists() {
        return productInShoppingLists;
    }

    public void setProductInShoppingLists(List<ProductInShoppingList> productInShoppingLists) {
        this.productInShoppingLists = productInShoppingLists;
    }
}
