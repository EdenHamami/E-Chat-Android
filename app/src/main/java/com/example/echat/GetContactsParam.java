package com.example.echat;

import com.google.gson.annotations.SerializedName;

public class GetContactsParam {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("server")
    private String server;

    @SerializedName("last")
    private String last;

    @SerializedName("lastdate")
    private String lastdate;

    public GetContactsParam getContact() {
        return this;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getServer() {
        return server;
    }
    public String getLast() {
        return last;
    }
    public String getLastDate() {
        return lastdate;
    }
}
