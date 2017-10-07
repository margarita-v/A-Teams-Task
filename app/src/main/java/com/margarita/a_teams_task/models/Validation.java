package com.margarita.a_teams_task.models;

import com.google.gson.annotations.SerializedName;
import com.margarita.a_teams_task.models.base.BaseModel;

public class Validation extends BaseModel {

    @SerializedName(OBJECT_FIELD)
    private String objectOrArray;

    private String error;

    @SerializedName(ERROR_INFO_FIELD)
    private String errorInfo;

    private boolean empty;

    private boolean validate;

    private int size;

    @SerializedName(PARSE_TIME_FIELD)
    private int parseTimeNanoseconds;

    //region Field's names
    private static final String OBJECT_FIELD = "object_or_array";
    private static final String ERROR_FIELD = "error";
    private static final String ERROR_INFO_FIELD = "error_info";
    private static final String EMPTY_FIELD = "empty";
    private static final String VALIDATE_FIELD = "validate";
    private static final String SIZE_FIELD = "size";
    private static final String PARSE_TIME_FIELD = "parse_time_nanoseconds";
    //endregion

    @Override
    public String toString() {
        String result = getFieldInfo(OBJECT_FIELD, objectOrArray) + endOfLine +
                getFieldInfo(VALIDATE_FIELD, Boolean.toString(validate)) + endOfLine;
        if (this.validate)
            result += getFieldInfo(EMPTY_FIELD, Boolean.toString(empty)) + endOfLine +
                    getFieldInfo(PARSE_TIME_FIELD, Integer.toString(parseTimeNanoseconds)) + endOfLine +
                    getFieldInfo(SIZE_FIELD, Integer.toString(size));
        else
            result += getFieldInfo(ERROR_FIELD, error) + endOfLine +
                    getFieldInfo(ERROR_INFO_FIELD, errorInfo);
        return result;
    }
}
