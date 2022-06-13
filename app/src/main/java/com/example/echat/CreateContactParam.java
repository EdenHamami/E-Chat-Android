package com.example.echat;

import com.google.gson.annotations.SerializedName;

public class CreateContactParam {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("server")
    private String server;

    public CreateContactParam(String id, String name, String server) {
        this.id = id;
        this.name = name;
        this.server = server;
    }
}
