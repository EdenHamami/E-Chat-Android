package com.example.echat;

import com.google.gson.annotations.SerializedName;

public class CreateMessageParam {

    @SerializedName("content")
    private String content;

    public CreateMessageParam(String content) {
        this.content = content;

    }

}
