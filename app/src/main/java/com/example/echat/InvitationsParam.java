package com.example.echat;

import com.google.gson.annotations.SerializedName;

public class InvitationsParam {

    @SerializedName("from")
    private String from;

    @SerializedName("to")
    private String to;

    @SerializedName("server")
    private String server;



    public InvitationsParam(String from, String to, String server) {
        this.from = from;
        this.to = to;
        this.server = server;
    }
}
