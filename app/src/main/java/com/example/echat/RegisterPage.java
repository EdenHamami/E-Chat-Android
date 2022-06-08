package com.example.echat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class RegisterPage extends AppCompatActivity {
    private TextView here;
    private EditText userName,displayName,password,repeatPassword;
    private Button uploadPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        here=findViewById(R.id.create_account);
        userName=findViewById(R.id.UserName);
        displayName=findViewById(R.id.DisplayName);
        password=findViewById(R.id.Password);
        repeatPassword=findViewById(R.id.RepeatPassword);
        uploadPhoto=findViewById(R.id.uploadPhoto);
        here.setOnClickListener(v->{
            Intent i =new Intent(this,MainActivity.class);
            startActivity(i);
        });
        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent=new Intent(Intent.ACTION_PICK);
                photoIntent.setType("image/*");
                startActivityForResult(photoIntent,1);
                
            }
        });
    }

    public boolean validateUserName(){
        if(userName.getText().toString().length()<6){
            userName.setError("UserName must contain 6 letters");
            return false;
        }
        else{
            userName.setError(null);
            return true;
        }

    }
    public boolean validateDisplayName(){
        if(displayName.getText().toString().length()<6){
            displayName.setError("UserName must contain 6 letters");
            return false;
        }
        else{
            displayName.setError(null);
            return true;
        }

    }
    public boolean validatePassword(){
        if(password.getText().toString().length()<6){
            password.setError("Password Must Be At Least 6 Characters");
            return false;
        }
        else if(!Pattern.compile("[0-9]").matcher(password.getText().toString()).find()){
            password.setError("Your Password must contain numbers");
            return false;
        }
        else if(!Pattern.compile("[A-Z]").matcher(password.getText().toString()).find()&&
                !Pattern.compile("[a-z]").matcher(password.getText().toString()).find()){
            password.setError("Your Password must contain letters");
            return false;
        }

        else{
            password.setError(null);
            return true;
        }

    }
    public boolean validateRepeatPassword(){
        if(!repeatPassword.getText().toString().equals(password.getText().toString())) {
            repeatPassword.setError("Passwords are not the same");
            return false;
        }
        else{
            repeatPassword.setError(null);
            return true;
        }

    }
    public void registerUser(View view){
        if(!validateUserName()||!validateDisplayName()||!validatePassword()||!validateRepeatPassword()){
            return;
        }
        else {
            Intent i =new Intent(this,chatPage.class);
            startActivity(i);
        }
    }
}