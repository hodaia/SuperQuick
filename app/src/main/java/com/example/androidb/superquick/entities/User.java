package com.example.androidb.superquick.entities;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseQuery;

@ParseClassName("User")
public class User extends ParseObject {

    private int userId;
    private String userEmail;
    private String userPassword;
    private String userName;


    public User() {
        //  User userReference = ParseObject.createWithoutData(User.class,);
    }

    public User(int userId, String userEmail, String userPassword, String userName) {
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

    public static User getUserByEmail(String email) {
        ParseQuery<User> queryUser = ParseQuery.getQuery("User");
        queryUser.whereMatches("userEmail", "email");
        User user = null;
        try {
            //if there is already a userSession
            user = queryUser.getFirst();
        } catch (ParseException e) {
        }
        return user;
    }
}
