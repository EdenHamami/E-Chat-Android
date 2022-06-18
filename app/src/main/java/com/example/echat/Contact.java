package com.example.echat;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Contact {
@PrimaryKey(autoGenerate = true)
    private int contactId;

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

    private String userName;

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public void setId(String id) {
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

    public void setUserName(String userName) {
        this.userName = userName;
    }



//    public void setMessages(List<Message> messages) {
//        this.messages = messages;
//    }



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

    public Contact(String id, String name, String server, String last, String lastdate, String userName) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
        this.userName = userName;
        //this.messages = messages;
    }

    public Contact getContact() { return this; }

    public int getContactId() {
        return contactId;
    }

    public String getId() {
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

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString(){
        return "CONTACT{"+"id=" +id+" ,name="+name+'\''+"}";
    }
}
