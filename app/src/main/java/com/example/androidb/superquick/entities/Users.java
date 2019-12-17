package com.example.androidb.superquick.entities;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Users")
public class Users extends ParseObject {

    private int userId;
    public String userEmail;
    private String userPassword;
    private String userName;
    private int user_cityId;

    public Users() {
        //  User userReference = ParseObject.createWithoutData(User.class,);
    }

    public Users(int userId, String userEmail, String userPassword, String userName,int user_cityId) {
        setUserId(userId);
        setUserEmail(userEmail);
        setUserPassword(userPassword);
        setUserName(userName);
        setUser_cityId(user_cityId);
    }

    public int getUserId() {
        return getInt("userId");
    }

    public void setUserId(int userId) {
        put("userId", userId);
    }

    public String getUserEmail() {
        return getString("userEmail");
    }

    public void setUserEmail(String userEmail) {
        put("userEmail", userEmail);
    }

    public String getUserPassword() {
        return getString("userPassword");
    }

    public void setUserPassword(String userPassword) {
        put("userPassword", userPassword);
    }

    public String getUserName() {
        return getString("userName");
    }

    public void setUserName(String userName) {
        put("userName", userName);
    }

    public int getUser_cityId() { return getInt("user_cityId"); }

    public void setUser_cityId(int user_cityId) {
        put("user_cityId",user_cityId);
    }

    //user queries

    public static Users getUserByEmail(String email) {
        ParseQuery<Users> queryUser = ParseQuery.getQuery("Users");
        queryUser.whereMatches("userEmail", email);
        Users user = null;
        try {
            //if there is already a userSession
            user = queryUser.getFirst();
        } catch (ParseException e) {
        }
        return user;
    }
}
