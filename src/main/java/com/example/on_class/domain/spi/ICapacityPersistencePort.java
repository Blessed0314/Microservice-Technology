package com.example.on_class.domain.spi;

import com.example.on_class.domain.model.Capacity;

import java.util.List;

public interface ICapacityPersistencePort {
    void saveCapacity(Capacity capacity);
    List<Capacity> getAllCapacities(
            Integer page, Integer size, boolean orderFlag, boolean ascendingFlag);
}
