package com.example.echat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateUserParam {

    @SerializedName("userName")
    private String userName;

    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String password;

    @SerializedName("picture")
    private String picture;



    public CreateUserParam(String userName, String name, String password, String picture) {
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.picture = picture;
    }
}
