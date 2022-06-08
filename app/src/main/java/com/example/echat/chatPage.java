package com.example.echat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class chatPage extends AppCompatActivity {
private TextView contactName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactName=findViewById(R.id.contactName);

        setContentView(R.layout.activity_chat_page);
    }
}