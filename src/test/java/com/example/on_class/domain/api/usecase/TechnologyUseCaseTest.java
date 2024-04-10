package com.example.on_class.domain.api.usecase;

import com.example.on_class.domain.api.ITechnologyServicePort;
import com.example.on_class.domain.model.Technology;
import com.example.on_class.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TechnologyUseCaseTest {
    @Mock
    private ITechnologyPersistencePort technologyPersistencePort;
    private ITechnologyServicePort technologyServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        technologyServicePort = new TechnologyUseCase(technologyPersistencePort);
    }

    @Test
    void testSaveTechnology() {

        Technology technology = new Technology(2L,"Java","Programing Language");

        technologyServicePort.saveTechnology(technology);

        verify(technologyPersistencePort, times(1)).saveTechnology(technology);
    }

    @Test
    void testGetAllTechnologies() {
        List<Technology> expectedTechnologyList = new ArrayList<>();
        expectedTechnologyList.add(new Technology(2L,"Java","Programing Language"));
        expectedTechnologyList.add(new Technology(3L,"Python","Programming Language"));

        when(technologyPersistencePort.getAllTechnologies(1,10,true)).thenReturn(expectedTechnologyList);

        List<Technology> actualTechnologyList = technologyServicePort.getAllTechnologies(1,10,true);

        assertEquals(expectedTechnologyList, actualTechnologyList);
    }
}