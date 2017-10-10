package com.margarita.a_teams_task.models.base;

/**
 * Base class for all models in RecyclerViewAdapter
 */

public abstract class BaseModel {

    private static final String colon = " : ";

    protected char endOfLine = '\n';

    protected String getFieldInfo(String fieldName, String fieldValue) {
        return fieldName + colon + fieldValue;
    }

    public abstract String getClassName();
}
