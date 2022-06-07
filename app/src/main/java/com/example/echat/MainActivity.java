package com.example.echat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        setContentView(R.layout.activity_main);
        create_account=findViewById(R.id.create_account);
        create_account.setOnClickListener(v->{
            Intent i =new Intent(this,RegisterPage.class);
            startActivity(i);
        });


        getUsers();

    }

    private void getUsers() {
        Call<List<Results>> call = RetrofitClient.getInstance().getMyApi().getUsers();
        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                List<Results> myUserList = response.body();
                String[] users = new String[myUserList.size()];

                for (int i = 0; i < myUserList.size(); i++) {
                    users[i] = myUserList.get(i).getName();
                }

                //superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, users));
            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }
}