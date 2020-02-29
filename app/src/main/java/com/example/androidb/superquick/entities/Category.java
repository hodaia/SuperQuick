package com.example.androidb.superquick.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseQuery;

@ParseClassName("Category")
public class Category extends ParseObject {
    private int categoryId;
    private String categoryName;

    public Category() {

    }

    public Category(int categoryId, String categoryName) {
        setCategoryId(categoryId);
        setCategoryName(categoryName);
    }

    public interface CategoryString{
        public String categoryId = "categoryId";
        public String categoryName = "categoryName";
    }
    public int getCategoryId() {
        return getInt(CategoryString.categoryId);
    }

    public void setCategoryId(int categoryId) {
        put(CategoryString.categoryId, categoryId);
    }

    public String getCategoryName() {
        return getString(CategoryString.categoryName);
    }

    public void setCategoryName(String categoryName) {
        put(CategoryString.categoryName, categoryName);
    }

    public static HashMap<Integer, HashMap<SubCategory, List<Product>>> getAllProductsByCategory() {
        List<Category> ParsedCategories=new ArrayList<>();
        List<SubCategory> parsedSubCategories= new ArrayList<>();
        HashMap<SubCategory,List<Product>> parsedProducts= new HashMap<>();
        HashMap<Integer, HashMap<SubCategory, List<Product>>> allProductsByCategory=new HashMap<>();

        ParsedCategories=Category.getCategories();
        for (Category c:ParsedCategories) {
            parsedSubCategories=SubCategory.getSubCategory(c.getCategoryId());
            parsedProducts=Product.getProductsBySubCategory(parsedSubCategories);

            allProductsByCategory.put(c.categoryId,parsedProducts);
        }
        return allProductsByCategory;
    }
    //Category Queries
    public static List<Category> getCategories() {
        List<Category> ParsedCategories=new ArrayList<>();
        ParseQuery<Category> queryCategory = ParseQuery.getQuery("Category");

        try {
            ParsedCategories = queryCategory.find();
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return ParsedCategories;
    }
}
