package com.example.androidb.superquick.entities;

import com.example.androidb.superquick.General.UserSessionData;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ParseClassName("ProductInSuper")
public class ProductInSuper extends ParseObject {
    private int productInSuper_superId;
    private int productInSuper_productId;
    private int productInSuperPrice;
    private int productInSuper_columnId;
    private int productInSuper_rowId;

    public ProductInSuper() {
    }

    public ProductInSuper(int productInSuper_superId, int productInSuper_productId, int productInSuper_price,int productInSuper_columnId,int productInSuper_rowId) {
        setProductInSuper_productId(productInSuper_productId);
        setProductInSuper_superId(productInSuper_superId);
        setProductInSuperPrice(productInSuper_price);
        setProductInSuper_columnId(productInSuper_columnId);
        setProductInSuper_rowId(productInSuper_rowId);
    }

    public int getProductInSuper_superId() {
        return getInt("productInSuper_superId");
    }

    public void setProductInSuper_superId(int productInSuper_superId) {
        put("productInSuper_superId", productInSuper_superId);
    }

    public int getProductInSuper_productId() {
        return getInt("productInSuper_productId");
    }

    public void setProductInSuper_productId(int productInSuper_productId) {
        put("productInSuper_productId", productInSuper_productId);
    }

    public int getProductInSuperPrice() {
        return getInt("productInSuperPrice");
    }

    public void setProductInSuperPrice(int productInSuperPrice) {
        put("productInSuperPrice", productInSuperPrice);
    }

    public int getProductInSuper_columnId() {
        return getInt("productInSuper_columnId");
    }

    public void setProductInSuper_columnId(int productInSuper_columnId) {
        put("productInSuper_columnId", productInSuper_columnId);
    }

    public int getProductInSuper_rowId() {
        return getInt("productInSuper_rowId");
    }

    public void setProductInSuper_rowId(int productInSuper_rowId) {
        put("productInSuper_rowId", productInSuper_rowId);
    }

    //productInSuper quries

    public static float shoppingListCostInSuper(int superId) {
        int s = 0;
        int shoppingListId;
        if (UserSessionData.getInstance().userCurrentShoppingListId == 0)
            shoppingListId = UserSessionData.getInstance().userShoppingList.getShoppingListId();
        else
            shoppingListId = UserSessionData.getInstance().userCurrentShoppingListId;

        List<ProductInShoppingList> parsedproductInShoppingList = new ArrayList<>();
        ParseQuery<ProductInShoppingList> productInShoppingListQuery = ParseQuery.getQuery("ProductInShoppingList");
        productInShoppingListQuery.whereEqualTo("productInShoppingList_shoppingListId", shoppingListId);
        productInShoppingListQuery.orderByDescending("productInShoppingList_productId");
        try {
            parsedproductInShoppingList = productInShoppingListQuery.find();
        } catch (Exception e) {
        }

        ParseQuery<ProductInSuper> productInSuperQuery = ParseQuery.getQuery("ProductInSuper");
        productInSuperQuery.whereEqualTo("productInSuper_superId", superId);
        productInSuperQuery.whereMatchesKeyInQuery("productInSuper_productId", "productInShoppingList_productId", productInShoppingListQuery);
        productInSuperQuery.orderByDescending("productInSuper_productId");

        List<ProductInSuper> parsedProductInSuper = new ArrayList<>();
        try {
            parsedProductInSuper = productInSuperQuery.find();
        } catch (Exception e) {
        }

        for (int i = 0; i < parsedproductInShoppingList.size(); i++) {
            s += parsedproductInShoppingList.get(i).getProductInShoppingListAmount() * parsedProductInSuper.get(i).getProductInSuperPrice();
        }
        return (float) s;
    }

    public static List<ProductInSuper> cartProductPriceInSuper() {
        int cartProductPrice = 0;

        int shoppingListId;
        if (UserSessionData.getInstance().userCurrentShoppingListId == 0)
            shoppingListId = UserSessionData.getInstance().userShoppingList.getShoppingListId();
        else
            shoppingListId = UserSessionData.getInstance().userCurrentShoppingListId;

        List<ProductInShoppingList> parsedproductInShoppingList = new ArrayList<>();
        ParseQuery<ProductInShoppingList> productInShoppingListQuery = ParseQuery.getQuery("ProductInShoppingList");
        productInShoppingListQuery.whereEqualTo("productInShoppingList_shoppingListId", shoppingListId);
        productInShoppingListQuery.orderByDescending("productInShoppingList_productId");
        try {
            parsedproductInShoppingList = productInShoppingListQuery.find();
        } catch (Exception e) {
        }

        ParseQuery<ProductInSuper> productInSuperQuery = ParseQuery.getQuery("ProductInSuper");
        productInSuperQuery.whereEqualTo("productInSuper_superId", UserSessionData.getInstance().chosenSuperId);
        productInSuperQuery.whereMatchesKeyInQuery("productInSuper_productId", "productInShoppingList_productId", productInShoppingListQuery);
        productInSuperQuery.orderByDescending("productInSuper_productId");

        List<ProductInSuper> parsedProductInSuper = new ArrayList<>();
        try {
            parsedProductInSuper = productInSuperQuery.find();
        } catch (Exception e) {
        }

        return parsedProductInSuper;
    }

     public static HashMap<String,String> cartProductLocationInSuper() {
        int cartProductPrice = 0;

        int shoppingListId;
        if(UserSessionData.getInstance().mapShoopingListId==0) {
            if (UserSessionData.getInstance().userCurrentShoppingListId == 0)
                shoppingListId = UserSessionData.getInstance().userShoppingList.getShoppingListId();
            else
                shoppingListId = UserSessionData.getInstance().userCurrentShoppingListId;
        }
        else {
            shoppingListId=UserSessionData.getInstance().mapShoopingListId;
        }
        List<ProductInShoppingList> parsedproductInShoppingList = new ArrayList<>();
        ParseQuery<ProductInShoppingList> productInShoppingListQuery = ParseQuery.getQuery("ProductInShoppingList");
        productInShoppingListQuery.whereEqualTo("productInShoppingList_shoppingListId", shoppingListId);
        productInShoppingListQuery.orderByDescending("productInShoppingList_productId");
        try {
            parsedproductInShoppingList = productInShoppingListQuery.find();
        } catch (Exception e) {
        }

         List<Product> parsedProductInShoppingList = new ArrayList<>();
         ParseQuery<Product> productDataInShoppingListQuery = ParseQuery.getQuery("Product");
         productDataInShoppingListQuery.whereMatchesKeyInQuery("productId", "productInShoppingList_productId", productInShoppingListQuery);
         productDataInShoppingListQuery.orderByDescending("productInShoppingList_productId");
         try {
             parsedProductInShoppingList = productDataInShoppingListQuery.find();
         } catch (Exception e) {
         }

        ParseQuery<ProductInSuper> productInSuperQuery = ParseQuery.getQuery("ProductInSuper");
        productInSuperQuery.whereEqualTo("productInSuper_superId", 1);
        productInSuperQuery.whereMatchesKeyInQuery("productInSuper_productId", "productInShoppingList_productId", productInShoppingListQuery);
        productInSuperQuery.orderByDescending("productInSuper_productId");

        List<ProductInSuper> parsedProductInSuper = new ArrayList<>();
        try {
            parsedProductInSuper = productInSuperQuery.find();
        } catch (Exception e) {
        }

         HashMap<String,String> map = new HashMap<String,String>();
        //(
String s1,s2;
         for (int i=0;i<parsedProductInShoppingList.size();i++)
               {
                   s1=String.valueOf(parsedProductInSuper.get(i).getProductInSuper_columnId());
                   s2=String.valueOf(parsedProductInSuper.get(i).getProductInSuper_rowId());
                   map.put(s1+" "+s2,parsedProductInShoppingList.get(i).getProductDescription());
         }

         return map;
    }
}



