package com.example.On_class.domain.spi;

import com.example.On_class.domain.model.Capacity;

public interface ICapacityPersistencePort {
    void saveCapacity(Capacity capacity);
}
