package com.margarita.a_teams_task.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //region URLs
    public static final String GET_IP_URL = "http://ip.jsontest.com/";
    public static final String GET_HEADERS_URL = "http://headers.jsontest.com/";
    public static final String GET_DATETIME_URL = "http://date.jsontest.com/";
    public static final String GET_URL_VALIDATION = "http://validate.jsontest.com";
    // This URL is base
    private static final String GET_URL_JSON = "http://echo.jsontest.com";
    //endregion

    public static ApiInterface getApi() {
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GET_URL_JSON)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ApiInterface.class);
    }
}
