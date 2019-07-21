package com.example.androidb.superquick.entities;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseQuery;

@ParseClassName("Super")
public class Super extends ParseObject {
    private int superId;
    private String superName;


    public Super() {

    }

    public Super(int superId, String superName) {
        setSuperId(superId);
        setSuperName(superName);
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


    //Super Queries
    public static List<Super> getSupers() {
        List<Super> parsedSupers=null;
        ParseQuery<Super> querySupers = ParseQuery.getQuery("Super");
        try {
            parsedSupers = querySupers.find();
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return parsedSupers;
    }
}
