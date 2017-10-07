package com.margarita.a_teams_task.models;

import com.margarita.a_teams_task.models.base.BaseModel;

public class IpAddress extends BaseModel {

    private String ip;

    private static final String IP_FIELD = "ip";

    @Override
    public String toString() {
        return getFieldInfo(IP_FIELD, this.ip);
    }
}
