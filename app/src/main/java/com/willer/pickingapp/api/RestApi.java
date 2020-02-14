package com.willer.pickingapp.api;

import com.willer.pickingapp.model.Auth;
import com.willer.pickingapp.model.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {

    @Headers({
            "Client-Service: frontend-client",
            "Auth-Key: simplerestapi",
            "Content-Type:  application/x-www-form-urlencoded"
    })
    @POST("login")
    Call<Auth> authResponse(@Query("username") String username, @Query("password") String password);

    @Headers({
            "Client-Service: frontend-client",
            "Auth-Key: simplerestapi",
            "Content-Type:  application/x-www-form-urlencoded",
            "User-ID: 1",
    })
    @GET("client")
    Call<List<Client>> getClientList(@Header("Authorization") String authHeader);
}
