package com.margarita.a_teams_task.models;

import com.google.gson.annotations.SerializedName;
import com.margarita.a_teams_task.models.base.BaseModel;

public class DateTime extends BaseModel {

    private String time;

    @SerializedName("milliseconds_since_epoch")
    private String millisecondsSinceEpoch;

    private String date;

    @Override
    public String toString() {
        return getFieldInfo("time", this.time) + endOfLine +
                getFieldInfo("milliseconds_since_epoch", this.millisecondsSinceEpoch) + endOfLine +
                getFieldInfo("date", this.date);
    }
}
