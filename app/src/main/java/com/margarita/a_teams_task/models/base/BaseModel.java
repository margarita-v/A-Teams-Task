package com.margarita.a_teams_task.models.base;

/**
 * Base class for all models in RecyclerViewAdapter
 */

public class BaseModel {

    private static final char divider = '\"';

    private static final String colon = " : ";

    protected char endOfLine = '\n';

    protected String getFieldInfo(String fieldName, String fieldValue) {
        return divider + fieldName + divider + colon + divider + fieldValue + divider;
    }
}
