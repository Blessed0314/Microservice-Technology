package com.example.On_class.domain.api.usecase;

import com.example.On_class.domain.exception.RepeatTechnologiesInListException;
import com.example.On_class.domain.api.ICapacityServicePort;
import com.example.On_class.domain.model.Capacity;
import com.example.On_class.domain.model.Technology;
import com.example.On_class.domain.spi.ICapacityPersistencePort;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CapacityUseCase implements ICapacityServicePort {
    private ICapacityPersistencePort capacityPersistencePort;
    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort) {
        this.capacityPersistencePort = capacityPersistencePort;
    }
    @Override
    public void saveCapacity(Capacity capacity) {
        List<Technology> technologies = capacity.getTechnologies();
        Set<Long> technologyIds = new HashSet<>();
        for (Technology technology : technologies) {
            Long techId = technology.getId();
            if (!technologyIds.add(techId)) {
                throw new RepeatTechnologiesInListException();
            }
        }capacityPersistencePort.saveCapacity(capacity);
    }

    @Override
    public List<Capacity> getAllCapacities(Integer page, Integer size, boolean orderFlag, boolean ascendingFlag) {
        return capacityPersistencePort.getAllCapacities(page, size, orderFlag, ascendingFlag);
    }
}
