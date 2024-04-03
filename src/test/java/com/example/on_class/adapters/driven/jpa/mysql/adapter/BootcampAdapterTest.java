package com.example.on_class.adapters.driven.jpa.mysql.adapter;

import com.example.on_class.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.example.on_class.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.example.on_class.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.example.on_class.domain.model.Bootcamp;
import com.example.on_class.domain.model.Capacity;
import com.example.on_class.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BootcampAdapterTest {
    @Mock
    private IBootcampRepository bootcampRepository;
    @Mock
    private IBootcampEntityMapper bootcampEntityMapper;
    private BootcampAdapter bootcampAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bootcampAdapter = new BootcampAdapter(bootcampRepository, bootcampEntityMapper);
    }

    @Test
    void saveBootcamp_SuccessfullySaved() {
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language" ));

        List<Capacity> capacities = new ArrayList<>();
        capacities.add(new Capacity(2L, "Proof2", "Fronted", technologies));

        Bootcamp bootcamp = new Bootcamp(1L,"Proof3", "Bootcamp description", capacities);
        BootcampEntity bootcampEntity = new BootcampEntity();

        when(bootcampRepository.findByName(bootcamp.getName())).thenReturn(Optional.empty());
        when(bootcampEntityMapper.toEntity(bootcamp)).thenReturn(bootcampEntity);

        bootcampAdapter.saveBootcamp(bootcamp);

        verify(bootcampRepository).save(bootcampEntity);
    }
}