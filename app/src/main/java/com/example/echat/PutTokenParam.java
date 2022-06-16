package com.example.echat;

import com.google.gson.annotations.SerializedName;

public class PutTokenParam {

    @SerializedName("userName")
    private String userName;

    @SerializedName("token")
    private String token;



    public PutTokenParam(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }
}
