package com.example.echat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contact {

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

    //public string Picture { get; set; }

    @SerializedName("messages")
    private List<Message> messages;


    public Contact(String id, String name, String server, String last, String lastdate, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }
}
