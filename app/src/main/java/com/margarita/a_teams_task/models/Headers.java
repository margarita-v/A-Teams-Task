package com.margarita.a_teams_task.models;

import com.google.gson.annotations.SerializedName;
import com.margarita.a_teams_task.models.base.BaseModel;

public class Headers extends BaseModel {

    @SerializedName(ACCEPT_LANGUAGE_FIELD)
    private String acceptLanguage;

    @SerializedName(HOST_FIELD)
    private String host;

    @SerializedName(USER_AGENT_FIELD)
    private String userAgent;

    //region Field's names
    private static final String CLASS_NAME = "Headers";
    private static final String ACCEPT_LANGUAGE_FIELD = "Accept-Language";
    private static final String HOST_FIELD = "Host";
    private static final String USER_AGENT_FIELD = "User-Agent";
    //endregion

    @Override
    public String toString() {
        return getFieldInfo(ACCEPT_LANGUAGE_FIELD, acceptLanguage) + endOfLine +
                getFieldInfo(HOST_FIELD, host) + endOfLine +
                getFieldInfo(USER_AGENT_FIELD, userAgent);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}
