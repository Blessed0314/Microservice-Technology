package com.example.on_class.domain.api.usecase;

import com.example.on_class.domain.api.ITechnologyServicePort;
import com.example.on_class.domain.model.Technology;
import com.example.on_class.domain.spi.ITechnologyPersistencePort;

import java.util.List;


public class TechnologyUseCase implements ITechnologyServicePort {
    private ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }
    @Override
    public void saveTechnology(Technology technology) {
        technologyPersistencePort.saveTechnology(technology);
    }

    @Override
    public List<Technology> getAllTechnologies(Integer page, Integer size, Boolean orderFlag) {
        return technologyPersistencePort.getAllTechnologies(page, size, orderFlag);
    }
}
