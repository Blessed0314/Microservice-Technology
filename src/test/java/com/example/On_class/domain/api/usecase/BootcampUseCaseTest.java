package com.example.On_class.domain.api.usecase;

import com.example.On_class.domain.api.IBootcampServicePort;
import com.example.On_class.domain.exception.RepeatCapacitiesInListException;
import com.example.On_class.domain.model.Bootcamp;
import com.example.On_class.domain.model.Capacity;
import com.example.On_class.domain.model.Technology;
import com.example.On_class.domain.spi.IBootcampPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class BootcampUseCaseTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Mock
    private IBootcampPersistencePort bootcampPersistencePort;
    private IBootcampServicePort bootcampServicePort;
    private BootcampUseCase bootcampUseCase;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        bootcampServicePort = new BootcampUseCase(bootcampPersistencePort);
        bootcampUseCase = new BootcampUseCase(bootcampPersistencePort);
    }

    @Test
    void testSaveBootcamp() {
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language" ));

        List<Capacity> capacities = new ArrayList<>();
        capacities.add(new Capacity(1L, "Proof2", "Fronted", technologies));

        Bootcamp bootcamp = new Bootcamp(1L, "Proof3", "bootcamp description", capacities);
        bootcampServicePort.saveBootcamp(bootcamp);

        verify(bootcampPersistencePort, times(1)).saveBootcamp(bootcamp);
    }

    @Test
    void testValidationRepeatCapacity() {
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language"));

        List<Capacity> capacities = new ArrayList<>();
        Capacity capacity = new Capacity(1L, "Proof2", "Fronted", technologies);
        capacities.add(capacity);
        capacities.add(capacity);

        Bootcamp bootcamp = new Bootcamp(1L,"Proof3", "Bootcamp description", capacities);

        assertThrows(RepeatCapacitiesInListException.class, () -> bootcampUseCase.saveBootcamp(bootcamp));
        verify(bootcampPersistencePort, times(0)).saveBootcamp(bootcamp);
    }

}