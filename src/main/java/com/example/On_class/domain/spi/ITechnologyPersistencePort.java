package com.example.On_class.domain.spi;

import com.example.On_class.domain.model.Technology;



public interface ITechnologyPersistencePort {
    void saveTechnology(Technology technology);
    //List
}
