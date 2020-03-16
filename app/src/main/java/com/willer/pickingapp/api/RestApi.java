package com.willer.pickingapp.api;

import com.willer.pickingapp.entities.Price;
import com.willer.pickingapp.model.Auth;
import com.willer.pickingapp.entities.Client;
import com.willer.pickingapp.entities.Product;

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
    @GET("ClientController")
    Call<List<Client>> getClientList(@Header("Authorization") String authHeader);

    @Headers({
            "Client-Service: frontend-client",
            "Auth-Key: simplerestapi",
            "Content-Type:  application/x-www-form-urlencoded",
            "User-ID: 1",
    })
    @GET("ProductController")
    Call<List<Product>> getProductList(@Header("Authorization") String authHeader);

    @Headers({
            "Client-Service: frontend-client",
            "Auth-Key: simplerestapi",
            "Content-Type:  application/x-www-form-urlencoded",
            "User-ID: 1",
    })
    @GET("PriceController")
    Call<List<Price>> getPriceList(@Header("Authorization") String authHeader);
}
