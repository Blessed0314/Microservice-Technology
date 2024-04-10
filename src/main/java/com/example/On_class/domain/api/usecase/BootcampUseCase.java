package com.example.On_class.domain.api.usecase;

import com.example.On_class.domain.exception.RepeatCapacitiesInListException;
import com.example.On_class.domain.api.IBootcampServicePort;
import com.example.On_class.domain.model.Bootcamp;
import com.example.On_class.domain.model.Capacity;
import com.example.On_class.domain.spi.IBootcampPersistencePort;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BootcampUseCase implements IBootcampServicePort {
    private IBootcampPersistencePort bootcampPersistencePort;
    public BootcampUseCase(IBootcampPersistencePort bootcampPersistencePort){
        this.bootcampPersistencePort = bootcampPersistencePort;
    }
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
        bootcampPersistencePort.saveBootcamp(bootcamp);
    }
}
