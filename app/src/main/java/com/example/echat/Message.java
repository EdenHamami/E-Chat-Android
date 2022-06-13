package com.example.echat;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @SerializedName("content")
    private String content;

    @SerializedName("created")
    private String created;

    @SerializedName("to")
    private String to;

    @SerializedName("from")
    private String from;

    public Message(String content, String created, String to, String from) {
        this.content = content;
        this.created = created;
        this.to = to;
        this.from = from;

    }

    public int getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public String getCreated() {
        return created;
    }
    public String getTo() {
        return to;
    }
    public String getFrom() {
        return from;
    }

    public void setId(int id) {
        this.id = id;
    }






}
