package com.example.androidb.superquick.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseQuery;

@ParseClassName("SubCategory")
public class SubCategory extends ParseObject   implements Comparable<SubCategory>, Comparator<SubCategory> {

    private int subCategoryId;
    private String subCategoryName;
    private Category subCategory_categoryCode;

    public SubCategory() {
    }

    public SubCategory(int subCategoryId, String subCategoryName, Category subCategory_categoryCode) {

        setSubCategoryId(subCategoryId);
        setSubCategoryName(subCategoryName);
        setSubCategory_categoryCode(subCategory_categoryCode);
    }

    public int getSubCategoryId() { return getInt("subCategoryId"); }

    public void setSubCategoryId(int subCategoryId) { put("subCategoryId",subCategoryId); }

    public String getSubCategoryName() { return getString("subCategoryName"); }

    public void setSubCategoryName(String subCategoryName) { put("subCategoryName",subCategoryName); }

    public Category getSubCategory_categoryCode() {
        return subCategory_categoryCode;
    }

    public void setSubCategory_categoryCode(Category subCategory_categoryCode) {
        this.subCategory_categoryCode = subCategory_categoryCode;
    }

    @Override
    public int compareTo(SubCategory o) {
        return this.subCategoryId = o.subCategoryId;
    }

    @Override
    public int compare(SubCategory o, SubCategory o1) {
        return 0;
    }

    //SubCategory Queries

    public  static List<SubCategory> getSubCategory(int categoryId){
        List<SubCategory> ParsedSubCategories=new ArrayList<>();

        ParseQuery<SubCategory> querySubCategories = ParseQuery.getQuery("SubCategory");
        ParseQuery<Category> queryCurrentCategory = ParseQuery.getQuery("Category");
        queryCurrentCategory.whereEqualTo("categoryId",categoryId);
        querySubCategories.whereMatchesQuery("subCategory_categoryCode",queryCurrentCategory);

        try {
            ParsedSubCategories=querySubCategories.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  ParsedSubCategories;

    }
}
