package com.example.echat;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Contact {
@PrimaryKey(autoGenerate=true)
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("server")
    private String server;

    @SerializedName("last")
    private String last;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

//    public void setMessages(List<Message> messages) {
//        this.messages = messages;
//    }

    @SerializedName("lastdate")
    private String lastdate;

    //public string Picture { get; set; }

//    @SerializedName("messages")
//  private List<Message> messages;


//    public Contact(int id, String name, String server, String last, String lastdate, List<Message> messages) {
//        this.id = id;
//        this.name = name;
//       // this.server = server;
//        this.last = last;
//        this.lastdate = lastdate;
//        //this.messages = messages;
//    }

    public Contact(int id, String name, String server, String last, String lastdate) {
        this.id = id;
        this.name = name;
        // this.server = server;
        this.last = last;
        this.lastdate = lastdate;
        //this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public String getServer() {
        return server;
    }

    public String getLast() {
        return last;
    }

    public String getLastdate() {
        return lastdate;
    }
//
//    public List<Message> getMessages() {
//        return messages;
//    }

    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return "CONTACT{"+"id=" +id+" ,name="+name+'\''+"}";
    }
}
