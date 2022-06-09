package com.example.echat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class chatPage extends AppCompatActivity {
    private TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username=findViewById(R.id.contactName);
        String contactName=username.getText().toString();
        Intent activityIntent=getIntent();
        if(activityIntent!=null){
            contactName=activityIntent.getStringExtra("Username");

        }


        setContentView(R.layout.activity_chat_page);
    }
}