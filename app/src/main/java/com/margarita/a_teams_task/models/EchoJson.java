package com.margarita.a_teams_task.models;

import com.margarita.a_teams_task.models.base.BaseModel;

public class EchoJson extends BaseModel {

    private String key;

    public static final String CLASS_NAME = "Echo JSON";
    public static final String KEY_FIELD = "key";

    @Override
    public String toString() {
        return getFieldInfo(KEY_FIELD, key);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}
