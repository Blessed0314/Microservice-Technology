package com.example.On_class.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
public class Capacity {
    private final Long id;
    private final String name;
    private final String description;
    @Setter
    private Set<Technology> technologies;

    public Capacity(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
