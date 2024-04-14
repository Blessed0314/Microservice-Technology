package com.example.on_class.adapters.driving.http.util;

public final class MessageConstants {
    private MessageConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_NAME_MAX_LENGTH_MESSAGE = "Field 'name' must have a minimum of 4 or a maximum of 50 characters";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE = "Field 'description' must have a minimum of 10 or a maximum of 90 characters";
    public static final String FIELD_LIST_TECHNOLOGIES_EMPTY_MESSAGE = "Field 'Technologies' cannot be empty";
    public static final String FIELD_LIST_TECHNOLOGIES_LENGTH_MESSAGE = "Field 'Technologies' must have a minimum of 3 or a maximum of 20 technologies";
    public static final String FIELD_LIST_CAPACITIES_EMPTY_MESSAGE = "Field 'Capacities' cannot be empty";
    public static final String FIELD_LIST_CAPACITIES_LENGTH_MESSAGE = "Field 'Capacities' must have a minimum of 1 or a maximum of 4 capacities";
    public static final String FIELD_QUOTA_GREATER_THAN_0_OR_NOT_NULL = "Field 'Quota' cannot be null and must be greater than 0 ";
    public static final String FIELD_INITIAL_DATE_NULL_MESSAGE = "Field 'Initial Date' cannot be null";
    public static final String FIELD_END_DATE_NULL_MESSAGE = "Field 'End Date' cannot be null";

}
