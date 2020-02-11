package com.willer.pickingapp.api;

import com.willer.pickingapp.model.Auth;

import retrofit2.Call;
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
}
