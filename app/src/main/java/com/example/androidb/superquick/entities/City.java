package com.example.androidb.superquick.entities;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ParseClassName("City")
public class City extends ParseObject {
    private int cityId;
    private String cityName;

    public City() {

    }

    public City(int cityId, String cityName) {
        setCityId(cityId);
       setCityName(cityName);
    }

    public int getCityId() {
        return getInt("cityId");
    }

    public void setCityId(int cityId) {
        put("cityId", cityId);
    }

    public String getCityName() {
        return getString("cityName");
    }

    public void setCityName(String cityName) {
        put("cityName", cityName);
    }


    //Super Queries
    public static List<City> getCities() {
        List<City> parsedCities=new ArrayList<>();
        ParseQuery<City> queryCities = ParseQuery.getQuery("City");
        //queryCities.selectKeys(Arrays.asList("cityName"));
        try {
            parsedCities = queryCities.find();
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return parsedCities;
    }



}
