package com.example.echat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://localhost:7213/api/contacts/users/";
    @GET("marvel")
    Call<List<Results>> getUsers();
}