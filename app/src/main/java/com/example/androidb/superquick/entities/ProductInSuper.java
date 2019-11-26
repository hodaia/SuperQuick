package com.example.androidb.superquick.entities;

import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseQuery;

import java.util.ArrayList;
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

    public ProductInSuper(int productInSuper_superId, int productInSuper_productId, int productInSuper_price) {
        setProductInSuper_productId(productInSuper_productId);
        setProductInSuper_superId(productInSuper_superId);
        setProductInSuperPrice(productInSuper_price);
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
        put("productInSuper_columnId",productInSuper_columnId);
    }

    public int getProductInSuper_rowId() {
        return getInt("productInSuper_rowId");
    }

    public void setProductInSuper_rowId(int productInSuper_rowId) {
        put("productInSuper_rowId",productInSuper_rowId);
    }

    //productInSuper quries


    public static float shoppingListCostInSuper(int superId, int shoppingListId) {
        int s=0;
        List<ProductInShoppingList> parsedproductInShoppingList=new ArrayList<>();
        ParseQuery<ProductInShoppingList> productInShoppingListQuery= ParseQuery.getQuery("ProductInShoppingList");
        productInShoppingListQuery.whereEqualTo("productInShoppingList_shoppingListId",shoppingListId);
        productInShoppingListQuery.orderByDescending("productInShoppingList_shoppingListId");
        try {
            parsedproductInShoppingList = productInShoppingListQuery.find();
        } catch (Exception e) {
        }

        ParseQuery<ProductInSuper> productInSuperQuery= ParseQuery.getQuery("ProductInSuper");
        //productInSuperQuery.whereEqualTo("productInSuper_superId",superId);
        productInSuperQuery.whereMatchesKeyInQuery("productInSuper_productId","productInShoppingList_productId",productInShoppingListQuery);
        productInSuperQuery.orderByDescending("productInSuper_productId");

        List<ProductInSuper> parsedProductInSuper=new ArrayList<>();
        try {
            parsedProductInSuper = productInSuperQuery.find();
        } catch (Exception e) {
        }

        for (int i=0;i<parsedproductInShoppingList.size();i++){
            s+=parsedproductInShoppingList.get(i).getProductInShoppingListAmount()*parsedProductInSuper.get(i).getProductInSuperPrice();
        }
        return (float)s;
    }

}
