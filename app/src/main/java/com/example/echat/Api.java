package com.example.echat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://localhost:7213/api/contacts/";
    @GET("users")
    Call<List<User>> getUsers();

    @POST("users")
    Call<List<String>> createUser(@Body List<String> user);

    @POST("{UserName}")
    Call<Contact> createContact(@Body Contact contact);

    @POST("{UserName}/{id}/messages")
    Call<Message> createMessage(@Body Message message);
}