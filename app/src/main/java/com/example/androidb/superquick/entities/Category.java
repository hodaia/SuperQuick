package com.example.androidb.superquick.entities;

import java.util.ArrayList;
import java.util.Date;
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


    //Category Queries
    public static List<Category> getCategories() {
        List<Category> ParsedCategories=null;
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