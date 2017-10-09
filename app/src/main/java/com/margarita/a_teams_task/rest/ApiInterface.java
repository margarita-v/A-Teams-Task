package com.margarita.a_teams_task.rest;

import com.margarita.a_teams_task.models.DateTime;
import com.margarita.a_teams_task.models.EchoJson;
import com.margarita.a_teams_task.models.Headers;
import com.margarita.a_teams_task.models.IpAddress;
import com.margarita.a_teams_task.models.Validation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET
    Call<IpAddress> getIpAddress(@Url String url);

    @GET
    Call<Headers> getHeaders(@Url String url);

    @GET
    Call<DateTime> getDateAndTime(@Url String url);

    @GET("key/{" + EchoJson.KEY_FIELD + "}")
    Call<EchoJson> getEchoJson(@Path(EchoJson.KEY_FIELD) String value);

    @GET("?json={json}")
    Call<Validation> checkValidation(@Path("json") String json);
}
