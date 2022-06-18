package com.example.echat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "http://10.0.2.2:7213/api/";
    @GET("contacts/users")
    Call<List<User>> getUsers();

    @POST("contacts/users")
    Call<Void> createUser(@Body CreateUserParam user);

    @GET("contacts/{UserName}")
    Call<List<GetContactsParam>> getContacts(@Path("UserName") String UserName);

    @POST("contacts/{UserName}")
    Call<Void> createContact(@Path("UserName") String UserName, @Body CreateContactParam contact);

    @GET("contacts/{UserName}/{id}/messages")
    Call<List<GetMessagesParam>> getMessages(@Path("UserName") String UserName, @Path("id") String id);

    @POST("contacts/{UserName}/{id}/messages")
    Call<Void> createMessage(@Path("UserName") String UserName, @Path("id") String id, @Body CreateMessageParam message);

    @POST("invitations")
    Call<InvitationsParam> NewConversation(@Body InvitationsParam newConversation);

    @POST("transfer")
    Call<Void> NewMessage(@Body TransferParam newMessage);

    @POST("token")
    Call<Void> PutToken(@Body PutTokenParam newToken);
}