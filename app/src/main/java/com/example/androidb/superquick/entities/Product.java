package com.example.androidb.superquick.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.example.androidb.superquick.General.UserSessionData;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseQuery;

@ParseClassName("Product")
public class Product extends ParseObject {

    private int productId;
    private String productName;
    private String productDescription;
    private SubCategory product_subCategoryCode;


    public Product() {
    }

    public Product(int productId, String productName, String productDescription, SubCategory product_subCategoryCode) {
        setProductId(productId);
        setProductName(productName);
        setProductDescription(productDescription);
        setProduct_subCategoryCode(product_subCategoryCode);
    }


    public int getProductId() {
        return getInt("productId");
    }

    public void setProductId(int productId) {
        put("productId", productId);
    }

    public String getProductName() {
        return getString("productName");
    }

    public void setProductName(String productName) {
        put("productName", productName);
    }

    public String getProductDescription() {
        return getString("productDescription");
    }

    public void setProductDescription(String productDescription) {
        put("productDescription", productDescription);
    }

    public SubCategory getProduct_subCategoryCode() {
        return product_subCategoryCode;
    }

    public void setProduct_subCategoryCode(SubCategory product_subCategoryCode) {
        this.product_subCategoryCode = product_subCategoryCode;
    }


   //Product Queries
    public static HashMap<SubCategory,List<Product>> getProductsBySubCategory(List<SubCategory> parsedSubCategories) {
        List<Product> ParsedProducts = null;

        ParseQuery<Product> queryProducts = ParseQuery.getQuery("Product");
        queryProducts.whereContainedIn("product_subCategoryCode", parsedSubCategories);

        //if the previous activity was shoppingListContent
        //which means there is a need to load a dinamic ShoppingListProcessActivity
        if (UserSessionData.getInstance().userShoppingList == null) {
            ParseQuery<ProductInShoppingList> queryProductInShoppingList = ParseQuery.getQuery("ProductInShoppingList");
            queryProductInShoppingList.whereEqualTo("productInShoppingList_shoppingListId", UserSessionData.getInstance().userCurrentShoppingListId);
            queryProducts.whereMatchesKeyInQuery("productId","productInShoppingList_shoppingListId", queryProductInShoppingList);
            queryProducts.selectKeys(Arrays.asList("productInShoppingListAmount"));
        }


        try {
            ParsedProducts = queryProducts.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        HashMap<SubCategory,List<Product>> integerListHashMap= new HashMap<>();
        List<Product>products=new ArrayList<>();
        for (SubCategory s:parsedSubCategories)
              {
                  for(Product p:ParsedProducts) {
                      if(p.getProduct_subCategoryCode().getSubCategoryId()==s.getSubCategoryId())
                          products.add(p);
                  }
                  integerListHashMap.put(s,products );
                  products.removeAll(products);
        }

        return  integerListHashMap;
    }

    public static List<Product> getCurrentCartList() {
        List<Product> ParsedProducts = new ArrayList<>();

        ParseQuery<ProductInShoppingList> queryCurrentShoppingList=ParseQuery.getQuery("ProductInShoppingList");
        queryCurrentShoppingList.whereEqualTo("productInShoppingList_shoppingListId", UserSessionData.getInstance().userShoppingList.getShoppingListId());
        ParseQuery<Product> queryCurrentProductCartList=ParseQuery.getQuery("Product");
        queryCurrentProductCartList.whereMatchesKeyInQuery("productId","productInShoppingList_productId", queryCurrentShoppingList);
        try {
            ParsedProducts = queryCurrentProductCartList.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ParsedProducts;
    }
}
