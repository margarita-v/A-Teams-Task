package com.margarita.a_teams_task.models;

import com.google.gson.annotations.SerializedName;
import com.margarita.a_teams_task.models.base.BaseModel;

public class DateTime extends BaseModel {

    private String time;

    @SerializedName(MILLISECONDS_FIELD)
    private String millisecondsSinceEpoch;

    private String date;

    //region Field's names
    private static final String TIME_FIELD = "time";
    private static final String MILLISECONDS_FIELD = "milliseconds_since_epoch";
    private static final String DATE_FIELD = "date";
    //endregion

    @Override
    public String toString() {
        return getFieldInfo(TIME_FIELD, this.time) + endOfLine +
                getFieldInfo(MILLISECONDS_FIELD, this.millisecondsSinceEpoch) + endOfLine +
                getFieldInfo(DATE_FIELD, this.date);
    }
}
