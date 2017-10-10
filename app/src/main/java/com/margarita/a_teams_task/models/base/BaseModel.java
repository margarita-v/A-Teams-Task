package com.margarita.a_teams_task.models.base;

/**
 * Base class for all models in RecyclerViewAdapter
 */

public abstract class BaseModel {

    private static final String colon = " : ";

    protected char endOfLine = '\n';

    /**
     * Function for getting info about each field in format "key : value"
     * @param fieldName Name of the field
     * @param fieldValue Value of the field
     * @return String in format "fieldName : fieldValue"
     */
    protected String getFieldInfo(String fieldName, String fieldValue) {
        return fieldName + colon + fieldValue;
    }

    /**
     * Name of every class which will extend BaseModel
     * @return Class name
     */
    public abstract String getClassName();
}
