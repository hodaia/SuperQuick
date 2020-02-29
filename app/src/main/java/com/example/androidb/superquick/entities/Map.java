package com.example.androidb.superquick.entities;

import com.example.androidb.superquick.General.UserSessionData;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

@ParseClassName("Map")
public class Map extends ParseObject {
    static private int mapId=1;
    private int map_userId;
    private int map_superId;
    private String map_superName;
    private int map_shoppingListId;
    private String map_shoppingListName;

    public Map() {

    }

    public Map(int map_userId, int map_superId, int map_shoppingListId,String map_superName,String map_shoppingListName) {
        setMapId(mapId++);
        setMap_userId(map_userId);
        setMap_superId(map_superId);
        setMap_shoppingListId(map_shoppingListId);
        setMap_superName(map_superName);
        setMap_shoppingListName(map_shoppingListName);
    }

    public int getMapId() {
        return getInt("mapId");
    }

    public void setMapId(int mapId) {
        put("mapId", mapId);
    }

    public int getMap_superId() {
        return getInt("map_superId");
    }

    public void setMap_superId(int map_superId) {
        put("map_superId", map_superId);
    }

    public int getMap_shoppingListId() {
        return getInt("map_shoppingListId");
    }

    public void setMap_shoppingListId(int map_shoppingListId) {
        put("map_shoppingListId", map_shoppingListId);
    }

    public int getMap_userId() {
        return getInt("map_userId");
    }

    public void setMap_userId(int map_userId) {
        put("map_userId", map_userId);
    }

    public String getMap_superName() {
        return getString("map_superName");
    }

    public void setMap_superName(String map_superName) {
        put("map_superName",map_superName);
    }

    public String getMap_shoppingListName() {
        return getString("map_shoppingListName");
    }

    public void setMap_shoppingListName(String map_shoppingListName) {
        put("map_shoppingListName", map_shoppingListName);
    }

    public static List<Map> getMapsByUserId() {

            List<Map> parsedMaps = new ArrayList<>();
            ParseQuery<Map> queryMaps = ParseQuery.getQuery("Map");
            queryMaps.whereEqualTo("map_userId", UserSessionData.getInstance().user.getUserId());
            try {
                parsedMaps = queryMaps.find();
            } catch (
                    ParseException e) {
                e.printStackTrace();
            }
            return parsedMaps;
        }

}