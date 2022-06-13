package com.example.echat;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//public class chatPage extends AppCompatActivity {
//    private TextView username;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        username=findViewById(R.id.contactName);
//        String contactName=username.getText().toString();
//        Intent activityIntent=getIntent();
//        if(activityIntent!=null){
//            contactName=activityIntent.getStringExtra("Username");
//
//        }
//
//
//        setContentView(R.layout.activity_chat_page);
//    }
//}
public class chatPage extends AppCompatActivity {
    private TextView contactName;
    private String contactUserName;
    private String contactNameString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        contactName=findViewById(R.id.contactName);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                contactUserName= null;
            } else {
                contactUserName= extras.getString("Username");
            }
        } else {
            contactUserName= (String) savedInstanceState.getSerializable("Username");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                contactNameString= null;
            } else {
                contactNameString= extras.getString("Name");
            }
        } else {
            contactNameString= (String) savedInstanceState.getSerializable("Name");
        }
        contactName.setText(contactNameString);
    }
}