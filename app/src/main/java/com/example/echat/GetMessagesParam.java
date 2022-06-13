package com.example.echat;

import com.google.gson.annotations.SerializedName;

public class GetMessagesParam {

    @SerializedName("content")
    private String content;

    @SerializedName("created")
    private String created;

    @SerializedName("sent")
    private Boolean sent;


    public GetMessagesParam(String content, String created, Boolean sent) {
        this.content = content;
        this.created = created;
        this.sent = sent;
    }

    public GetMessagesParam getMessage() {
        return this;
    }

    public String getContent() {
        return content;
    }

    public String getCreated() {
        return created;
    }

    public Boolean getSent() {
        return sent;
    }
}
