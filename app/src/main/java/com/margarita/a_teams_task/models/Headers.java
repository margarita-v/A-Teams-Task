package com.margarita.a_teams_task.models;

import com.google.gson.annotations.SerializedName;
import com.margarita.a_teams_task.models.base.BaseModel;

public class Headers extends BaseModel {

    @SerializedName(ACCEPT_LANGUAGE_FIELD)
    private String acceptLanguage;

    @SerializedName(HOST_FIELD)
    private String host;

    @SerializedName(REFERER_FIELD)
    private String referer;

    @SerializedName(ACCEPT_FIELD)
    private String accept;

    //region Field's names
    private static final String ACCEPT_LANGUAGE_FIELD = "Accept-Language";
    private static final String HOST_FIELD = "Host";
    private static final String REFERER_FIELD = "Referer";
    private static final String ACCEPT_FIELD = "Accept";
    //endregion

    @Override
    public String toString() {
        return getFieldInfo(ACCEPT_LANGUAGE_FIELD, acceptLanguage) + endOfLine +
                getFieldInfo(HOST_FIELD, host) + endOfLine +
                getFieldInfo(REFERER_FIELD, referer) + endOfLine +
                getFieldInfo(ACCEPT_FIELD, accept);
    }
}
