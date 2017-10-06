package com.margarita.a_teams_task.models;

import com.margarita.a_teams_task.models.base.BaseModel;

public class EchoJson extends BaseModel {

    private String value;

    @Override
    public String toString() {
        return getFieldInfo("key", value);
    }
}
