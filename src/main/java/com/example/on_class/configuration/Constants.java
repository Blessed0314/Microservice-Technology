package com.example.on_class.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("Utility class");
    }
    public static final String TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The technology you want to create already exists";
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String REPEAT_TECHNOLOGY_IN_LIST_EXCEPTION_MESSAGE = "There are repeated technologies in the capacity";
    public static final String REPEAT_CAPACITY_IN_LIST_EXCEPTION_MESSAGE = "There are repeated capacities in the bootcamp";
}
