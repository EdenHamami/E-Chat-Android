package com.example.echat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText userName;
    private EditText password;
    private TextView create_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userName=findViewById(R.id.UserName);
        password=findViewById(R.id.Password);
        setContentView(R.layout.activity_main);
        create_account=findViewById(R.id.create_account);
        create_account.setOnClickListener(v->{
            Intent i =new Intent(this,RegisterPage.class);
            startActivity(i);
        });


        getUsers();

    }

    private void getUsers() {
        Call<List<User>> call = RetrofitClient.getInstance().getMyApi().getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> myUserList = response.body();
                String[] users = new String[myUserList.size()];

                for (int i = 0; i < myUserList.size(); i++) {
                    users[i] = myUserList.get(i).getName();
                }

                //superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, users));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

    public boolean validUser(){
return true;

    }

    public void loginUser(View view){
        if(!validUser()){
            Snackbar mySnackbar = Snackbar.make(view, "Username or password is incorrect", BaseTransientBottomBar.LENGTH_LONG);
           mySnackbar.show();
            return;
        }
        else {
            Intent i =new Intent(this,chatPage.class);
            startActivity(i);
        }
    }
    }
