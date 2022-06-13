package com.example.echat;

import com.google.gson.annotations.SerializedName;

public class TransferParam {

    @SerializedName("from")
    private String from;

    @SerializedName("to")
    private String to;

    @SerializedName("content")
    private String content;



    public TransferParam(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }
}
