package com.example.on_class.domain.spi;

import com.example.on_class.domain.model.Technology;

import java.util.List;


public interface ITechnologyPersistencePort {
    void saveTechnology(Technology technology);
    List<Technology> getAllTechnologies(Integer page, Integer size, boolean orderFlag);
}
