package com.example.On_class.adapters.driven.jpa.mysql.adapter;

import com.example.On_class.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.example.On_class.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.On_class.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.example.On_class.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.example.On_class.domain.model.Capacity;
import com.example.On_class.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language" ));

        Capacity capacity = new Capacity(2L, "Proof2", "Fronted", technologies);
        CapacityEntity capacityEntity = new CapacityEntity();

        when(capacityRepository.findByName(capacity.getName())).thenReturn(Optional.empty());
        when(capacityEntityMapper.toEntity(capacity)).thenReturn(capacityEntity);

        capacityAdapter.saveCapacity(capacity);

        verify(capacityRepository).save(capacityEntity);
    }

    @Test
    void getAllTechnologies_Error_DataFound(){
        int page = 0;
        int size = 20;
        boolean ascendingFlag = true;
        boolean orderFlag = true;
        PageRequest pagination = PageRequest.of(page, size, Sort.by(ascendingFlag ? Sort.Direction.ASC : Sort.Direction.DESC, "name"));

        when(capacityRepository.findAll(pagination)).thenReturn(new PageImpl<>(Collections.emptyList()));

        assertThrows(NoDataFoundException.class, () -> capacityAdapter.getAllCapacities(page, size, orderFlag, ascendingFlag));

        verify(capacityRepository).findAll(pagination);
        verifyNoInteractions(capacityEntityMapper);
    }
}