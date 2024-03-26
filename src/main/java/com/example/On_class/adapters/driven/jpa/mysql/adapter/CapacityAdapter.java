package com.example.On_class.adapters.driven.jpa.mysql.adapter;

import com.example.On_class.adapters.driven.jpa.mysql.exception.RepeatTechnologiesInListException;
import com.example.On_class.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.example.On_class.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.example.On_class.domain.model.Capacity;
import com.example.On_class.domain.model.Technology;
import com.example.On_class.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class CapacityAdapter implements ICapacityPersistencePort {
    private final ICapacityRepository capacityRepository;
    private final ICapacityEntityMapper capacityEntityMapper;


    @Override
    public void saveCapacity(Capacity capacity) {
        List<Technology> technologies = capacity.getTechnologies();
        Set<Long> technologyIds = new HashSet<>();
        for (Technology technology : technologies) {
            Long techId = technology.getId();
            if (!technologyIds.add(techId)) {
                throw new RepeatTechnologiesInListException();
            }
        }
        capacityRepository.save(capacityEntityMapper.toEntity(capacity));
    }
}
