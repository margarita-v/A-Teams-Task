package com.margarita.a_teams_task.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //region URLs
    // URLs for common items
    public static final String GET_IP_URL = "http://ip.jsontest.com/";
    public static final String GET_HEADERS_URL = "http://headers.jsontest.com/";
    public static final String GET_DATETIME_URL = "http://date.jsontest.com/";
    // URLs for forms
    public static final String GET_URL_JSON = "http://echo.jsontest.com";
    public static final String GET_URL_VALIDATION = "http://validate.jsontest.com";
    //endregion

    public static ApiInterface getApi() {
        return configureApi(null);
    }

    public static ApiInterface getApi(String url) {
        return configureApi(url);
    }

    private static ApiInterface configureApi(String url) {
        Gson gson = new GsonBuilder().create();

        if (url == null)
            url = GET_URL_JSON;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ApiInterface.class);
    }
}
