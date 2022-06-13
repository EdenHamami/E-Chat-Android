package com.example.echat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class chatPage extends AppCompatActivity {
  //  private TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        username=findViewById(R.id.contactName);
//        String contactName=username.getText().toString();
//        Intent activityIntent=getIntent();
//        if(activityIntent!=null){
//            contactName=activityIntent.getStringExtra("Username");
//
//        }


        setContentView(R.layout.activity_chat_page);
    }
}