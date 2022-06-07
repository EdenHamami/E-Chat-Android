package com.example.echat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Message {

    @SerializedName("id")
    private int id;

    @SerializedName("content")
    private String content;

    @SerializedName("created")
    private String created;

    @SerializedName("sent")
    private Boolean sent;

    public Message(int id, String content, String created, Boolean sent) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.sent = sent;

    }
}
