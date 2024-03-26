package com.example.On_class.adapters.driven.jpa.mysql.adapter;

import com.example.On_class.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.example.On_class.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.example.On_class.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.example.On_class.domain.model.Capacity;
import com.example.On_class.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CapacityAdapterTest {
    @Mock
    private ICapacityRepository capacityRepository;
    @Mock
    private ICapacityEntityMapper capacityEntityMapper;
    private CapacityAdapter capacityAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        capacityAdapter = new CapacityAdapter(capacityRepository, capacityEntityMapper);
    }

    @Test
    void saveCapacity_SuccessfullySaved() {
        Technology technology1 = new Technology(1L, "Java", "Programming Language" );
        List<Technology> technologies = new ArrayList<>();
        technologies.add(technology1);

        Capacity capacity = new Capacity(2L, "Proof2", "Fronted", technologies);
        CapacityEntity capacityEntity = new CapacityEntity();

        when(capacityRepository.findByName(capacity.getName())).thenReturn(Optional.empty());
        when(capacityEntityMapper.toEntity(capacity)).thenReturn(capacityEntity);

        capacityAdapter.saveCapacity(capacity);

        verify(capacityRepository).save(capacityEntity);
    }
}