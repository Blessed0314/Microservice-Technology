package com.example.on_class.domain.api;
import com.example.on_class.domain.model.Technology;

import java.util.List;


public interface ITechnologyServicePort {
    void saveTechnology(Technology technology);

    List<Technology> getAllTechnologies(Integer page, Integer size, Boolean orderFlag);

}
