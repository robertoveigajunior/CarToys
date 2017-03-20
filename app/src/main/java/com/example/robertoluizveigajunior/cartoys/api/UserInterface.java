package com.example.robertoluizveigajunior.cartoys.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.example.robertoluizveigajunior.cartoys.model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;



public interface UserInterface {
    String url = "http://www.mocky.io";

    @GET("/v2/58b9b1740f0000b614f09d2f")
    Call<User> getUser();

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
