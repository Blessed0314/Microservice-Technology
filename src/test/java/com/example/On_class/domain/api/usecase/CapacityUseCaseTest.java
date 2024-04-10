package com.example.On_class.domain.api.usecase;

import com.example.On_class.domain.api.ICapacityServicePort;
import com.example.On_class.domain.model.Capacity;
import com.example.On_class.domain.model.Technology;
import com.example.On_class.domain.spi.ICapacityPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CapacityUseCaseTest {

    @Mock
    private ICapacityPersistencePort capacityPersistencePort;
    private ICapacityServicePort capacityServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        capacityServicePort = new CapacityUseCase(capacityPersistencePort);
    }

    @Test
    void testSaveCapacity() {
        String name = "proof2";
        String description = "Fronted";
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language" ));
        Capacity capacity = new Capacity(1L, "Proof1", "Fronted", technologies);

        capacityServicePort.saveCapacity(capacity);

        verify(capacityPersistencePort, times(1)).saveCapacity(capacity);
    }
}