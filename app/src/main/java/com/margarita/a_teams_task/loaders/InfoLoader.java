package com.margarita.a_teams_task.loaders;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import com.margarita.a_teams_task.models.DateTime;
import com.margarita.a_teams_task.models.Headers;
import com.margarita.a_teams_task.models.IpAddress;
import com.margarita.a_teams_task.models.base.BaseModel;
import com.margarita.a_teams_task.rest.ApiClient;
import com.margarita.a_teams_task.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoLoader extends Loader<BaseModel> {

    private ApiInterface apiInterface;

    @Nullable
    private BaseModel item;

    private int currentId;

    private String request;

    //region Loaders' IDs
    public static final int LOADER_IP = 0;
    public static final int LOADER_HEADERS = 1;
    public static final int LOADER_DATETIME = 2;
    public static final int LOADER_JSON = 3;
    public static final int LOADER_VALIDATION = 4;

    public static final int[] ALL_LOADERS = { LOADER_IP, LOADER_HEADERS, LOADER_DATETIME, LOADER_JSON, LOADER_VALIDATION };
    //endregion

    //region URLs
    private static final String GET_IP_URL = "http://ip.jsontest.com/";
    private static final String GET_HEADERS_URL = "http://headers.jsontest.com/";
    private static final String GET_DATETIME_URL = "http://date.jsontest.com/";
    //endregion

    public InfoLoader(Context context, int id) {
        super(context);
        this.currentId = id;
        this.apiInterface = ApiClient.getApi();
    }

    public InfoLoader(Context context, int id, String request) {
        this(context, id);
        this.request = request;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (item != null)
            deliverResult(item);
        else
            forceLoad();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        switch (currentId) {
            case LOADER_IP:
                loadIpAddress();
                break;
            case LOADER_HEADERS:
                loadHeaders();
                break;
            case LOADER_DATETIME:
                loadDateTime();
                break;
            case LOADER_JSON:
                postJson();
                break;
            case LOADER_VALIDATION:
                postValidation();
                break;
        }
    }

    //region Load different items
    private void loadIpAddress() {
        Call<IpAddress> callItem = apiInterface.getIpAddress(GET_IP_URL);
        callItem.enqueue(new Callback<IpAddress>() {
            @Override
            public void onResponse(Call<IpAddress> call, Response<IpAddress> response) {
                item = response.body();
                deliverResult(item);
            }

            @Override
            public void onFailure(Call<IpAddress> call, Throwable t) {
                deliverResult(null);
            }
        });
    }

    private void loadHeaders() {
        Call<Headers> callHeaders = apiInterface.getHeaders(GET_HEADERS_URL);
        callHeaders.enqueue(new Callback<Headers>() {
            @Override
            public void onResponse(Call<Headers> call, Response<Headers> response) {
                item = response.body();
                deliverResult(item);
            }

            @Override
            public void onFailure(Call<Headers> call, Throwable t) {
                deliverResult(null);
            }
        });
    }

    private void loadDateTime() {
        Call<DateTime> callDateTime = apiInterface.getDateAndTime(GET_DATETIME_URL);
        callDateTime.enqueue(new Callback<DateTime>() {
            @Override
            public void onResponse(Call<DateTime> call, Response<DateTime> response) {
                item = response.body();
                deliverResult(item);
            }

            @Override
            public void onFailure(Call<DateTime> call, Throwable t) {
                deliverResult(null);
            }
        });
    }

    private void postJson() {

    }

    private void postValidation() {

    }
    //endregion
}
