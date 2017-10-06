package com.margarita.a_teams_task.models;

import com.google.gson.annotations.SerializedName;

public class Headers {

    @SerializedName("Accept-Language")
    private String acceptLanguage;

    @SerializedName("Host")
    private String host;

    @SerializedName("Referer")
    private String referer;

    @SerializedName("Accept")
    private String accept;

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public String getHost() {
        return host;
    }

    public String getReferer() {
        return referer;
    }

    public String getAccept() {
        return accept;
    }
}
