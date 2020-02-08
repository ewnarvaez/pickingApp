package com.willer.pickingapp.api;

import com.willer.pickingapp.model.Auth;

import retrofit2.Call;
import retrofit2.http.POST;

public interface RestApi {

    @POST("login")
    Call<Auth> authResponse();
}
