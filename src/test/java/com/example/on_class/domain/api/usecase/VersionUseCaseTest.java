package com.example.on_class.domain.api.usecase;

import com.example.on_class.domain.api.IVersionServicePort;
import com.example.on_class.domain.exception.IncorrectEndDateException;
import com.example.on_class.domain.exception.IncorrectInitialDateException;
import com.example.on_class.domain.model.Bootcamp;
import com.example.on_class.domain.model.Capacity;
import com.example.on_class.domain.model.Technology;
import com.example.on_class.domain.model.Version;
import com.example.on_class.domain.spi.IVersionPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VersionUseCaseTest {
    @Mock
    private IVersionPersistencePort versionPersistencePort;
    private IVersionServicePort versionServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        versionServicePort = new VersionUseCase(versionPersistencePort);
    }

    @Test
    void testSaveValidVersion() {
        LocalDate today = LocalDate.now();
        LocalDate initialDate = today.plusDays(1);
        LocalDate endDate = today.plusDays(10);

        Version version = new Version(1L, 30, initialDate, endDate, createBootcamp());

        versionServicePort.saveVersion(version);

        verify(versionPersistencePort, times(1)).saveVersion(version);
    }

    @Test
    void testSaveVersionWithIncorrectEndDate() {

        LocalDate today = LocalDate.now();
        LocalDate initialDate = today.plusDays(10);
        LocalDate endDate = today.plusDays(5);

        Version version = new Version(1L, 30, initialDate, endDate, createBootcamp());

        assertThrows(IncorrectEndDateException.class, () -> {
            versionServicePort.saveVersion(version);
        });

        verify(versionPersistencePort, never()).saveVersion(version);
    }

    @Test
    void testSaveVersionWithIncorrectInitialDate() {
        LocalDate today = LocalDate.now();
        LocalDate initialDate = today.minusDays(1);
        LocalDate endDate = today.plusDays(10);

        Version version = new Version(1L, 30, initialDate, endDate, createBootcamp());

        assertThrows(IncorrectInitialDateException.class, () -> {
            versionServicePort.saveVersion(version);
        });

        verify(versionPersistencePort, never()).saveVersion(version);
    }

    private Bootcamp createBootcamp() {
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language"));

        List<Capacity> capacities = new ArrayList<>();
        capacities.add(new Capacity(1L, "Proof2", "Fronted", technologies));

        return new Bootcamp(1L, "Proof3", "bootcamp description", capacities);
    }
}