package com.example.On_class.domain.model;

import com.example.On_class.domain.model.exception.EmptyFieldException;
import com.example.On_class.domain.model.util.DomainConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Technology {
    private final Long id;
    @NotBlank(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
    @Size(max = 50, message = DomainConstants.FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE)
    private final String name;
    @Size(max = 90, message = DomainConstants.FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE)
    private final String description;
    //private Capability capability

    public Technology(long id, String name, String description){

        this.id = id;
        this.name = name;
        this.description = description;
        //this.capability = capability;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
