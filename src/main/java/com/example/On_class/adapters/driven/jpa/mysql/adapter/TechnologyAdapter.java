package com.example.On_class.adapters.driven.jpa.mysql.adapter;


import com.example.On_class.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.example.On_class.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.example.On_class.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.example.On_class.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.example.On_class.domain.model.Technology;
import com.example.On_class.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;


    @Override
    public void saveTechnology(Technology technology) {
        Optional<TechnologyEntity> existingTechnology = technologyRepository.findByName(technology.getName());
        if (existingTechnology.isPresent()){
            throw new TechnologyAlreadyExistsException();
        }
        technologyRepository.save(technologyEntityMapper.toEntity(technology));
    }
}
