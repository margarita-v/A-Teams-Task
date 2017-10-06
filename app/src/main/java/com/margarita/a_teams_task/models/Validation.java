package com.margarita.a_teams_task.models;

import com.google.gson.annotations.SerializedName;

public class Validation {

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

    public String getObjectOrArray() {
        return objectOrArray;
    }

    public String getError() {
        return error;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isValidate() {
        return validate;
    }

    public int getSize() {
        return size;
    }

    public int getParseTimeNanoseconds() {
        return parseTimeNanoseconds;
    }
}
