package com.margarita.a_teams_task.rest;

import com.margarita.a_teams_task.models.DateTime;
import com.margarita.a_teams_task.models.EchoJson;
import com.margarita.a_teams_task.models.Headers;
import com.margarita.a_teams_task.models.IpAddress;
import com.margarita.a_teams_task.models.Validation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET
    Call<IpAddress> getIpAddress(@Url String url);

    @GET
    Call<Headers> getHeaders(@Url String url);

    @GET
    Call<DateTime> getDateAndTime(@Url String url);

    //TODO Change url
    @POST("http://echo.jsontest.com/key/")
    Call<EchoJson> postJson(@Body String json);

    @POST("http://validate.jsontest.com/?json=")
    Call<Validation> checkValidation(@Body String json);
}
