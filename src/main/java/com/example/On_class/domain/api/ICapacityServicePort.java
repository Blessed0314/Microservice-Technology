package com.example.On_class.domain.api;

import com.example.On_class.domain.model.Capacity;
import java.util.List;

public interface ICapacityServicePort {
    void saveCapacity(Capacity capacity);
    List<Capacity> getAllCapacities(Integer page, Integer size, boolean orderFlag, boolean ascendingFlag);
}
