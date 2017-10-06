package com.margarita.a_teams_task.models;

import com.google.gson.annotations.SerializedName;
import com.margarita.a_teams_task.models.base.BaseModel;

public class Validation extends BaseModel {

    @SerializedName("object_or_array")
    private String objectOrArray;

    private String error;

    @SerializedName("error_info")
    private String errorInfo;

    private boolean empty;

    private boolean validate;

    private int size;

    @SerializedName("parse_time_nanoseconds")
    private int parseTimeNanoseconds;

    @Override
    public String toString() {
        String result = getFieldInfo("object_or_array", objectOrArray) + endOfLine +
                getFieldInfo("validate", Boolean.toString(validate)) + endOfLine;
        if (this.validate)
            result += getFieldInfo("empty", Boolean.toString(empty)) + endOfLine +
                    getFieldInfo("parse_time_nanoseconds", Integer.toString(parseTimeNanoseconds)) + endOfLine +
                    getFieldInfo("size", Integer.toString(size));
        else
            result += getFieldInfo("error", error) + endOfLine +
                    getFieldInfo("error_info", errorInfo);
        return result;
    }
}
