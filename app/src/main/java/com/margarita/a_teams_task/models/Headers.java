package com.margarita.a_teams_task.models;

import com.google.gson.annotations.SerializedName;
import com.margarita.a_teams_task.models.base.BaseModel;

public class Headers extends BaseModel {

    @SerializedName("Accept-Language")
    private String acceptLanguage;

    @SerializedName("Host")
    private String host;

    @SerializedName("Referer")
    private String referer;

    @SerializedName("Accept")
    private String accept;

    @Override
    public String toString() {
        return getFieldInfo("Accept-Language", acceptLanguage) + endOfLine +
                getFieldInfo("Host", host) + endOfLine +
                getFieldInfo("Referer", referer) + endOfLine +
                getFieldInfo("Accept", accept);
    }
}
