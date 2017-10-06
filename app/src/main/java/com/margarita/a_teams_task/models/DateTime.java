package com.margarita.a_teams_task.models;

import com.google.gson.annotations.SerializedName;
import com.margarita.a_teams_task.models.base.BaseModel;

public class DateTime extends BaseModel {

    private String time;

    @SerializedName("milliseconds_since_epoch")
    private String millisecondsSinceEpoch;

    private String date;

    public String getTime() {
        return time;
    }

    public String getMillisecondsSinceEpoch() {
        return millisecondsSinceEpoch;
    }

    public String getDate() {
        return date;
    }
}
