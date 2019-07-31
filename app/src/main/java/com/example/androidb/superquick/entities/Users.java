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


    public Users() {
        //  User userReference = ParseObject.createWithoutData(User.class,);
    }

    public Users(int userId, String userEmail, String userPassword, String userName) {
        setUserId(userId);
        setUserEmail(userEmail);
        setUserPassword(userPassword);
        setUserName(userName);
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
