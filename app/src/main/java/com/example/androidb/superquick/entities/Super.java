package com.example.androidb.superquick.entities;

import java.util.ArrayList;
import java.util.List;

import com.example.androidb.superquick.General.UserSessionData;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseQuery;

@ParseClassName("Super")
public class Super extends ParseObject implements Comparable<Super>{
    private int superId;
    private String superName;
    private int super_cityId;
    public int totalPrice;

    public Super() {

    }

    public Super(int superId, String superName,int super_cityId) {
        setSuperId(superId);
        setSuperName(superName);
        setSuper_cityId(super_cityId);
    }

    public int getSuperId() {
        return getInt("superId");
    }

    public void setSuperId(int superId) {
        put("superId", superId);
    }

    public String getSuperName() {
        return getString("superName");
    }

    public void setSuperName(String superName) {
        put("superName", superName);
    }

    public int getSuper_cityId() {
        return getInt("super_cityId");
    }

    public void setSuper_cityId(int super_cityId) {
        put("super_cityId", super_cityId);
    }

    public void setTotalPrice(int totalPrice) {
       this.totalPrice=totalPrice;
    }
    public int getTotalPrice() { return  this.totalPrice; }

    //Super Queries
    public static List<Super> getSupers() {
        List<Super> parsedSupers=new ArrayList<>();
        ParseQuery<Super> querySupers = ParseQuery.getQuery("Super");
        try {
            parsedSupers = querySupers.find();
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return parsedSupers;
    }


    public static List<Column> getSuperColumns(){

        List<Column> parsedColumns=new ArrayList<>();
        ParseQuery<Column> queryColumns = ParseQuery.getQuery("Column");
        queryColumns.orderByAscending("columnId");
        //queryColumns.whereEqualTo("column_superId", UserSessionData.getInstance().chosenSuperId);
        try {
            parsedColumns = queryColumns.find();
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return parsedColumns;
    }

    public static List<Super> getSuperByCityId(int cityId){

        List<Super> parsedSupers=new ArrayList<>();
        ParseQuery<Super> querySupers = ParseQuery.getQuery("Super");
        querySupers.whereEqualTo("super_cityId",cityId);
        try {
            parsedSupers = querySupers.find();
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return parsedSupers;
    }

    @Override
    public int compareTo(Super o) {
        return this.getTotalPrice()-o.getTotalPrice();

    }
}
