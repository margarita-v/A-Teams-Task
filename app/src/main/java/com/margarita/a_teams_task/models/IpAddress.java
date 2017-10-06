package com.margarita.a_teams_task.models;

import com.margarita.a_teams_task.models.base.BaseModel;

public class IpAddress extends BaseModel {

    private String ip;

    @Override
    public String toString() {
        return getFieldInfo("ip", this.ip);
    }
}
