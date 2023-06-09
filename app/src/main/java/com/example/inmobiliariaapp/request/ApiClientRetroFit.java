package com.example.inmobiliariaapp.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientRetroFit {

    private static final String BASE_URL = "http://192.168.1.104:5200/";
    private static EndpointInmobiliaria endpoint;

    public static EndpointInmobiliaria getEndpoint() {

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        endpoint = retrofit.create(EndpointInmobiliaria.class);
        return endpoint;
    }
}
