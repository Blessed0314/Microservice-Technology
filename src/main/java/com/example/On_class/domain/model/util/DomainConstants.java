package com.example.On_class.domain.model.util;

public final class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION,
    }
    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_NAME_MAX_LENGTH_MESSAGE = "Field 'name' must have a maximum of 50 characters";
    public static final String FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE = "Field 'description' must have a maximum of 90 characters";
}
