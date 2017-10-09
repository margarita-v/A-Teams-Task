package com.margarita.a_teams_task.loaders;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import com.margarita.a_teams_task.models.DateTime;
import com.margarita.a_teams_task.models.EchoJson;
import com.margarita.a_teams_task.models.Headers;
import com.margarita.a_teams_task.models.IpAddress;
import com.margarita.a_teams_task.models.Validation;
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
                getEchoJson();
                break;
            case LOADER_VALIDATION:
                checkValidation();
                break;
        }
    }

    //region Load different items
    private void loadIpAddress() {
        Call<IpAddress> callItem = apiInterface.getIpAddress(ApiClient.GET_IP_URL);
        callItem.enqueue(new RetrofitCallback<IpAddress>());
    }

    private void loadHeaders() {
        Call<Headers> callHeaders = apiInterface.getHeaders(ApiClient.GET_HEADERS_URL);
        callHeaders.enqueue(new RetrofitCallback<Headers>());
    }

    private void loadDateTime() {
        Call<DateTime> callDateTime = apiInterface.getDateAndTime(ApiClient.GET_DATETIME_URL);
        callDateTime.enqueue(new RetrofitCallback<DateTime>());
    }

    private void getEchoJson() {
        Call<EchoJson> callJson = apiInterface.getEchoJson(request);
        callJson.enqueue(new RetrofitCallback<EchoJson>());
    }

    private void checkValidation() {
        Call<Validation> callValidation = apiInterface.checkValidation(ApiClient.GET_URL_VALIDATION, request);
        callValidation.enqueue(new RetrofitCallback<Validation>());
    }
    //endregion

    /**
     * Generic class for implementing callbacks for getting data
     * @param <T> Type of item which will be loaded
     */
    private class RetrofitCallback<T> implements Callback<T> {

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            item = (BaseModel) response.body();
            deliverResult(item);
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            deliverResult(null);
        }
    }
}
