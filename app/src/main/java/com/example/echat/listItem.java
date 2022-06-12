package com.example.echat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class listItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);


    }
public void contactChat(View v){
    Intent i =new Intent(this,ChatList.class);
    //i.putExtra("Username",userName.getText().toString());
    startActivity(i);
}
    }
