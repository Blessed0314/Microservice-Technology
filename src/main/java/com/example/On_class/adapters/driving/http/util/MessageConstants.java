package com.example.On_class.adapters.driving.http.util;

public final class MessageConstants {
    private MessageConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION,
    }
    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_NAME_MAX_LENGTH_MESSAGE = "Field 'name' must have a minimum of 4 or a maximum of 50 characters";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE = "Field 'description' must have a minimum of 10 or a maximum of 90 characters";
}
