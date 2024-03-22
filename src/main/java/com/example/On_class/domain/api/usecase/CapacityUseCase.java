package com.example.On_class.domain.api.usecase;

import com.example.On_class.domain.api.ICapacityServicePort;
import com.example.On_class.domain.model.Capacity;
import com.example.On_class.domain.spi.ICapacityPersistencePort;

public class CapacityUseCase implements ICapacityServicePort {
    private ICapacityPersistencePort capacityPersistencePort;
    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort) {
        this.capacityPersistencePort = capacityPersistencePort;
    }
    @Override
    public void saveCapacity(Capacity capacity) {
        capacityPersistencePort.saveCapacity(capacity);
    }
}
