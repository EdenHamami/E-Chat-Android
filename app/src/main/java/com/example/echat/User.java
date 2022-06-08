package com.example.echat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("userName")
    private String userName;

    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String password;

    @SerializedName("picture")
    private String picture;

    @SerializedName("myContacts")
    private List<Contact> myContacts;


    public User(String userName, String name, String password, String picture, List<Contact> myContacts) {
        this.userName = userName;
        this.name = name;
        this.password = password;
        //this.picture = picture;
        this.myContacts = myContacts;
    }

    public String getName() {
        return name;
    }
}