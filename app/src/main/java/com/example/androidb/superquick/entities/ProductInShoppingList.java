package com.example.androidb.superquick.entities;
import com.example.androidb.superquick.General.UserSessionData;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseQuery;

import java.util.List;

@ParseClassName("ProductInShoppingList")
public class ProductInShoppingList extends ParseObject {

    private int productInShoppingList_shoppingListId;
    public int productInShoppingList_productId;
    private int productInShoppingListAmount;

    public ProductInShoppingList() {
    }

    public ProductInShoppingList(int productInShoppingList_shoppingListId, int productInShoppingList_productId, int productInShoppingListAmount) {
        setProductInShoppingList_shoppingListId(productInShoppingList_shoppingListId);
        setProductInShoppingList_productId(productInShoppingList_productId);
        setProductInShoppingListAmount(productInShoppingListAmount);
    }

    public int getProductInShoppingList_shoppingListId() {
        return getInt("productInShoppingList_shoppingListId");
    }

    public void setProductInShoppingList_shoppingListId(int productInShoppingList_shoppingListId) {
        put("productInShoppingList_shoppingListId", productInShoppingList_shoppingListId);
    }

    public int getProductInShoppingList_productId() {
        return getInt("productInShoppingList_productId");
    }

    public void setProductInShoppingList_productId(int productInShoppingList_productId) {
        put("productInShoppingList_productId", productInShoppingList_productId);
    }

    public int getProductInShoppingListAmount() {
        return getInt("productInShoppingListAmount");
    }

    public void setProductInShoppingListAmount(int productInShoppingListAmount) {
        put("productInShoppingListAmount", productInShoppingListAmount);
    }


    //ProductInShoppingListQueries

    public static List<Product> getProductsOfShoppingList(int shoppingListId) {
        List<Product> parsedShoppingListContent=null;
        ParseQuery<ProductInShoppingList> ProductInShoppingListQuery = ParseQuery.getQuery("ProductInShoppingList");
        ProductInShoppingListQuery.whereEqualTo("productInShoppingList_shoppingListId", shoppingListId);
        ParseQuery<Product> ProductQuery = ParseQuery.getQuery("Product");
        ProductQuery.whereMatchesKeyInQuery("productId", "productInShoppingList_productId", ProductInShoppingListQuery);
        ProductQuery.orderByDescending("productId");
        try {
            parsedShoppingListContent = ProductQuery.find();
        } catch (Exception e) {
        }
        return parsedShoppingListContent;
    }

    public static List<Product> getProductsOfShoppingListInCart(int shoppingListId) {
        List<Product> parsedShoppingListContent=null;
        ParseQuery<ProductInShoppingList> ProductInShoppingListQuery = ParseQuery.getQuery("ProductInShoppingList");
        ProductInShoppingListQuery.whereEqualTo("productInShoppingList_shoppingListId", shoppingListId);
        ParseQuery<Product> ProductQuery = ParseQuery.getQuery("Product");
        ProductQuery.whereMatchesKeyInQuery("productId", "productInShoppingList_productId", ProductInShoppingListQuery);
        try {
            parsedShoppingListContent = ProductQuery.find();
        } catch (Exception e) {
        }
        return parsedShoppingListContent;
    }


    public static List<ProductInShoppingList> getProductsInShoppingList() {
        int shoppingListId;
        if(UserSessionData.getInstance().userCurrentShoppingListId==0)
            shoppingListId=UserSessionData.getInstance().userShoppingList.getShoppingListId();
        else
            shoppingListId=UserSessionData.getInstance().userCurrentShoppingListId;

        List<ProductInShoppingList> ParsedProducts = null;

        ParseQuery<ProductInShoppingList> queryCurrentShoppingList=ParseQuery.getQuery("ProductInShoppingList");
        queryCurrentShoppingList.whereEqualTo("productInShoppingList_shoppingListId",shoppingListId );
        queryCurrentShoppingList.orderByDescending("productId");
          try {
            ParsedProducts = queryCurrentShoppingList.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ParsedProducts;
    }
}