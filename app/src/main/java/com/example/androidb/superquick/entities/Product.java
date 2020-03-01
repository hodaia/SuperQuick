package com.example.androidb.superquick.entities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.example.androidb.superquick.General.UserSessionData;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

@ParseClassName("Product")
public class Product extends ParseObject {

    private int productId;
    private String productName;
    private String productDescription;
    private SubCategory product_subCategoryCode;
    private int subCategoryId;

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

    public int getSubCategoryId() {
        return getInt("subCategoryId");
    }

    public void setSubCategoryId(int subCategoryId) {
        put("subCategoryId", subCategoryId);
    }

    public int getProduct_subCategoryCode() {
        ParseRelation<SubCategory> relation = getRelation("product_subCategoryCode");
        int id;
        List<SubCategory> results1=new ArrayList<>();
        try {
             results1=relation.getQuery().find();

        } catch (ParseException e) {
            e.printStackTrace();
        }
         return results1.get(0).getSubCategoryId();


    }

    public void setProduct_subCategoryCode(SubCategory product_subCategoryCode) {
        put("product_subCategoryCode", product_subCategoryCode);
    }


   //Product Queries
    public static HashMap<SubCategory,List<Product>> getProductsBySubCategory(List<SubCategory> parsedSubCategories) {
        List<Product> ParsedProducts = null;

        //ParseQuery<Product> queryProducts = ParseQuery.getQuery("Product");
        //queryProducts.whereContainedIn("subCategoryId", parsedSubCategories);

//        try {
//            ParsedProducts = queryProducts.find();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        HashMap<SubCategory,List<Product>> integerListHashMap= new HashMap<>();
        List<Product>products=new ArrayList<>();
        for (SubCategory s:parsedSubCategories)
              {
                 // for(Product p:ParsedProducts) {
                      //int x=p.getProduct_subCategoryCode();
                      ParseQuery<Product> queryProducts = ParseQuery.getQuery("Product");
                      queryProducts.whereEqualTo("subCategoryId", s.getSubCategoryId());

                      try {
                          ParsedProducts = queryProducts.find();
                      } catch (ParseException e) {
                          e.printStackTrace();
                      }

                      //if(p.getProduct_subCategoryCode()==s.getSubCategoryId())
                      //    products.add(p);
                  //}
                  integerListHashMap.put(s,ParsedProducts);
                  products=new ArrayList<>();
        }
        return  integerListHashMap;
    }

    public static List<Product> getCurrentCartList() {
        int shoppingListId;
        if(UserSessionData.getInstance().userCurrentShoppingListId==0)
            shoppingListId=UserSessionData.getInstance().userShoppingList.getShoppingListId();
        else
            shoppingListId=UserSessionData.getInstance().userCurrentShoppingListId;

        List<Product> ParsedProducts = new ArrayList<>();

        ParseQuery<ProductInShoppingList> queryCurrentShoppingList=ParseQuery.getQuery("ProductInShoppingList");
        queryCurrentShoppingList.whereEqualTo("productInShoppingList_shoppingListId", shoppingListId);
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
