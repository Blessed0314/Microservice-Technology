package com.example.On_class.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
public class Technology {
    private final Long id;
    private final String name;
    private final String description;
    @Setter
    private Set<Capacity> capacities;

    public Technology(long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
