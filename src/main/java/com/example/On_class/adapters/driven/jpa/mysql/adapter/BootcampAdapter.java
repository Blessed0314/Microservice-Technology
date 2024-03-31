package com.example.On_class.adapters.driven.jpa.mysql.adapter;

import com.example.On_class.adapters.driven.jpa.mysql.exception.RepeatCapacitiesInListException;
import com.example.On_class.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.example.On_class.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.example.On_class.domain.model.Bootcamp;
import com.example.On_class.domain.model.Capacity;
import com.example.On_class.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class BootcampAdapter implements IBootcampPersistencePort {
    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;
    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        List<Capacity> capacities = bootcamp.getCapacities();
        Set<Long> capacityIds = new HashSet<>();
        for (Capacity capacity : capacities){
            Long capId = capacity.getId();
            if (!capacityIds.add(capId)){
                throw new RepeatCapacitiesInListException();
            }
        }
        bootcampRepository.save(bootcampEntityMapper.toEntity(bootcamp));
    }
}
